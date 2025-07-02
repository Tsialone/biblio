package s4.biblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.form.PretForm;
import s4.biblio.models.Abonnement;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.Exemplaire;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Pret;
import s4.biblio.models.Quota;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.PretRepository;

import java.time.LocalDate;
import java.util.List;

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

    public List<Pret> getAll() {
        return repository.findAll();
    }

    public void saveByForm(PretForm form) throws Exception {
        Pret pret = new Pret(
                null,
                form.getExemplaire(),
                form.getAdherant(),
                form.getDateDebut(),
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
        Quota user_quota = quotaService.getByCategorieAdherantType(user.getCategorie().getType());
        LocalDate user_date_fin_pret = user_date_debut_pret.plusDays(user_quota.getNombreJour());
        List<Pret> this_exemplaire_pret = getByExmemplaire(user_Exemplaire);
        for (Pret histo_pret : this_exemplaire_pret) {
                LocalDate histo_pret_debut_date = histo_pret.getDateDebut();
                Utilisateur adUtilisateur  = histo_pret.getAdherant();
                Quota quota = quotaService.getByCategorieAdherantType(adUtilisateur.getCategorie().getType());
                LocalDate histo_pret_fin_date = histo_pret_debut_date.plusDays(quota.getNombreJour());
                if (chevauchent(histo_pret_debut_date, histo_pret_fin_date, user_date_debut_pret, user_date_fin_pret)) {
                    throw new Exception("Cet exemplaire est a ete deja prete");
                }
        }
        repository.save(pret);
    }
    public static boolean chevauchent(LocalDate a, LocalDate b, LocalDate c, LocalDate d) {
        return !(b.isBefore(c) || d.isBefore(a));
    }

    public List<Pret> getByExmemplaire(Exemplaire exemplaire) {
        return this.repository.findByExemplaire(exemplaire);
    }
    // public List<Categorie> getByType (E_TypeCategorie type){
    // return repository.findByType(type);
    // }
}