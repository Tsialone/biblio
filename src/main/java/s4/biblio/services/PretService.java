package s4.biblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.form.PretForm;
import s4.biblio.form.RemiseForm;
import s4.biblio.models.Abonnement;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.Exemplaire;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Penalite;
import s4.biblio.models.Pret;
import s4.biblio.models.Quota;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.PretRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PretService {
    @Autowired
    private PretRepository repository;
    @Autowired
    private AbonnementService abonnementService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private HistoStatutService histoStatutService;
    @Autowired
    private QuotaService quotaService;
    @Autowired
    private JourFerieService jourFerieService;
    @Autowired
    private PenaliteService penaliteService;

    public Optional<Pret> getById(Integer id_pret) {
        return repository.findById(id_pret);
    }

    public List<Pret> getAll() {
        return repository.findAll();
    }

    public List<Pret> getByAdherant(Utilisateur adherant) {
        return repository.findByAdherant(adherant);
    }

    public void saveByRemiseForm(RemiseForm form) throws Exception {
        Pret pret = form.getPret();
        LocalDate pret_date_fin = pret.getDateFin();
        int nbr_jour = 2;
        Penalite penalite = new Penalite(null, pret, pret_date_fin, pret_date_fin.plusDays(nbr_jour));
        if (form.getDateRemise() == null) {
            penaliteService.save(penalite);
            throw new Exception("Vous avez ete penalise cause de non remise");
        }
        if (form.getDateRemise().isBefore(pret.getDateDebut())) {
            // penaliteService.save(penalite);
            throw new Exception("La remise doit etre dans le pret au moins");
        }
        // ato ilay penalite
        if (form.getDateRemise().isAfter(pret.getDateFin())) {
            penaliteService.save(penalite);
            throw new Exception("Vous avez ete penalise cause de retard");
        }
        // if (pret.getDateReprise() != null)
        // {
        // }
        // pret.setDateReprise(form.getDateRemise());
    }

    public void saveByForm(PretForm form) throws Exception {
        Pret pret = new Pret(
                null,
                form.getExemplaire(),
                form.getAdherant(),
                form.getDateDebut(),
                null,
                form.getCategorie(),
                null,
                null);

        Abonnement adh_abonnement = abonnementService.getByAdherantDate(pret.getDateDebut(), pret.getAdherant());
        Utilisateur user = pret.getAdherant();
        Exemplaire user_Exemplaire = pret.getExemplaire();

        HistoStatut current_histo_statut = histoStatutService.getCurrentByUtilisateur(user);

        // verification si l'adherant existe
        if (user == null) {
            throw new Exception("L'adherant non trouver");
        }
        // verification si l'exemplaire existe
        if (user_Exemplaire == null) {
            throw new Exception("Exemplaire non trouver");
        }
        // verification de l'age de l'adherant
        if (user_Exemplaire.getAgeMin() > user.getAge()) {
            throw new Exception("Les adherants d'age " + user.getAge() + " ne sont pas autorise, require "
                    + user_Exemplaire.getAgeMin());
        }
        // verification si l'utilisateur est es actif
        if (current_histo_statut == null) {
            throw new Exception("L'adherant " + pret.getAdherant().getId() + " | " + pret.getAdherant().getNom()
                    + " n'est pas actif");
        } else {
            Statut user_statut = current_histo_statut.getStatut();
            if (!user_statut.getLibelle().equalsIgnoreCase("actif")) {
                throw new Exception("L'adherant " + pret.getAdherant().getId() + " | " + pret.getAdherant().getNom()
                        + " n'est pas actif statut actuel " + user_statut.getLibelle());
            }
        }
        // verification si l'utilisateur est un abonnee
        if (adh_abonnement == null) {
            throw new Exception("L'adherant " + pret.getAdherant().getId() + " | " + pret.getAdherant().getNom()
                    + " n'est pas un abonnee");
        }

        // verification si l'exemplaire est disponible
        LocalDate user_date_debut_pret = pret.getDateDebut();
        Quota user_quota = quotaService.getByCategorieAdherant(user.getCategorie());
        LocalDate user_date_fin_pret = user_date_debut_pret.plusDays(user_quota.getNombreJour());

        // si sur place pas on applique pas le quotas
        if (pret.getCategorie().getLibelle().equalsIgnoreCase("sur place")) {
            user_date_fin_pret = pret.getDateDebut();
        }

        // prendre en compte les jours ferier
        int total_nbr_jour_pret_ferie = jourFerieService.nbrSautByFerieAndDate(user_date_fin_pret);
        System.out.println("debugggggggggggggggggggggggggggggg: " + total_nbr_jour_pret_ferie);
        System.out.println("avant:" + user_date_fin_pret);
        user_date_fin_pret = user_date_fin_pret.plusDays(total_nbr_jour_pret_ferie);
        System.out.println("apres:" + user_date_fin_pret);

        pret.setDateFin(user_date_fin_pret);

        Pret exemplaire_prete_dispo = estDispo(user_Exemplaire, user_date_debut_pret, user_date_fin_pret);
        if (exemplaire_prete_dispo != null) {
            throw new Exception(
                    "Cet exemplaire est a ete deja prete par " + exemplaire_prete_dispo.getAdherant().getId());
        }

        // Vérification si le prêt est bien dans la période de validité de l'abonnement
        if (pret.getDateDebut().isBefore(adh_abonnement.getDateDebut())
                || pret.getDateFin().isAfter(adh_abonnement.getDateFin())) {
            throw new Exception("Le prêt n'est pas compris dans la période de l'abonnement. "
                    + "Abonnement valide du " + adh_abonnement.getDateDebut() + " au " + adh_abonnement.getDateFin());
        }
        // verification de nombre de livre deja prise
        List<Pret> prets_user = getByAdherant(user);
        int nbr_pret_effectuer = prets_user.size();
        // mbola tss penalite
        int nbr_pret_max = user_quota.getNombreLivres();
        if (nbr_pret_effectuer + 1 > nbr_pret_max) {
            throw new Exception("L'adherant a atteint son quotas maximal max: " + nbr_pret_max);
        }
        // verification si il est penalise
        // List<LocalDate> intervall_peno = penaliteService.getIntervallePenaliteByAdherant(user);
        // if (!intervall_peno.isEmpty()) {
        //     if  (estDansIntervalle (pret.getDateDebut() , intervall_peno.getFirst() , intervall_peno.getLast()))  { 
        //         throw new Exception("L'utilsateur est penalise");
        //     }
        // }
          Penalite penalite = penaliteService.estDansUnePenalite(user_date_debut_pret, user_date_fin_pret, user);
            // List<LocalDate> intervall_peno =
            // penaliteService.getIntervallePenaliteByAdherant(user);
            // if (!intervall_peno.isEmpty()) {
            // if (pretService. estDansIntervalle(user_date_debut_pret,
            // intervall_peno.getFirst(), intervall_peno.getLast())) {
            // throw new Exception("L'utilsateur est penalise");
            // }
            // }
            if (penalite != null) {
                throw new Exception("L'utilsateur est penalise: " + penalite.getDateDebut() + " a " + penalite.getDateFin());
            }
        repository.save(pret);
    }

    public boolean estDansIntervalle(LocalDate dateCible, LocalDate debut, LocalDate fin) {
        return (dateCible.isEqual(debut) || dateCible.isAfter(debut)) &&
                (dateCible.isEqual(fin) || dateCible.isBefore(fin));
    }

    public void save(Pret pret) {
        repository.save(pret);
    }

    public static boolean chevauchent(LocalDate a, LocalDate b, LocalDate c, LocalDate d) {
        return !(b.isBefore(c) || d.isBefore(a));
    }

    public List<Pret> getByExmemplaire(Exemplaire exemplaire) {
        return this.repository.findByExemplaire(exemplaire);
    }

    public Pret estDispo(Exemplaire exemplaire, LocalDate date_debut, LocalDate date_fin) throws Exception {
        List<Pret> this_exemplaire_pret = getByExmemplaire(exemplaire);
        for (Pret histo_pret : this_exemplaire_pret) {
            LocalDate histo_pret_debut_date = histo_pret.getDateDebut();
            Utilisateur adUtilisateur = histo_pret.getAdherant();
            Quota quota = quotaService.getByCategorieAdherant(adUtilisateur.getCategorie());
            LocalDate histo_pret_fin_date = histo_pret_debut_date.plusDays(quota.getNombreJour());
            if (chevauchent(histo_pret_debut_date, histo_pret_fin_date, date_debut, date_fin)) {
                return histo_pret;
            }
        }
        return null;
    }
    // public List<Categorie> getByType (E_TypeCategorie type){
    // return repository.findByType(type);
    // }
}