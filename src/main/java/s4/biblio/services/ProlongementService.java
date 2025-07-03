package s4.biblio.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.models.*;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.ProlongementRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProlongementService {
    @Autowired
    private  ProlongementRepository repository;
    @Autowired
    private QuotaService quotaService;
    @Autowired
    private StatutService statutService;
    @Autowired
    private HistoStatutService histoStatutService;
    @Autowired
    private PretService pretService;
    @Autowired
    private AbonnementService abonnementService;


    public Optional<Prolongement> getById(Integer id) {
            return repository.findById(id);
    }
    public void updateStatutProlongement(Statut statut, Prolongement prolongement) throws Exception {
        Pret pret = prolongement.getPret();
        if (statut.getLibelle().equalsIgnoreCase("Annulée")) {
            // si la reservation est deja valider
            if (!prolongement.getStatut().getLibelle().equalsIgnoreCase("En attente")) {
                throw new Exception("Transaction deja close");
            }
            HistoStatut histoStatut = new HistoStatut(null, statut, null, null, null, null, null, prolongement);
            histoStatutService.save(histoStatut);
        } else if (statut.getLibelle().equalsIgnoreCase("Confirmée")) {
            Utilisateur user = prolongement.getPret().getAdherant();
            Statut user_statut = user.getStatut();
            Abonnement adh_abonnement = abonnementService.getByAdherantDate(prolongement.getPret().getDateDebut(), user);

            // si la reservation est deja valider
            if (!prolongement.getStatut().getLibelle().equalsIgnoreCase("En attente")) {
                throw new Exception("Transaction deja close");
            }
            // verification si l'utilisateur est est actif
            if (user_statut == null) {
                throw new Exception("L'adherant " + user.getId() + " | " + user.getNom()
                        + " n'est pas actif");
            } else {
                if (!user_statut.getLibelle().equalsIgnoreCase("actif")) {
                    throw new Exception("L'adherant " + user.getId() + " | " + user.getNom()
                            + " n'est pas actif statut actuel " + user_statut.getLibelle());
                }
            }
            // verification si l'utilisateur est un abonnee
            if (adh_abonnement == null) {
                throw new Exception("L'adherant " + user.getId() + " | " + user.getNom()
                        + " n'est pas un abonnee");
            }

            LocalDate user_date_debut_pret = prolongement.getPret().getDateDebut();
            LocalDate user_date_fin_pret = prolongement.getPret().getDateFin();

            if (pret.getCategorie().getLibelle().equalsIgnoreCase("Sur place")) {
                user_date_fin_pret = pret.getDateDebut();
            }
            Pret exemplaire_prete_dispo = pretService.estDispo(pret.getExemplaire(), user_date_debut_pret,
                    user_date_fin_pret);

            // verification si l'exemplaire est disponible
            if (exemplaire_prete_dispo != null) {
                throw new Exception(
                        "Cet exemplaire est a ete deja prete par " + exemplaire_prete_dispo.getAdherant().getId());
            }
            // Vérification si le prêt est bien dans la période de validité de l'abonnement
            if (user_date_debut_pret.isBefore(adh_abonnement.getDateDebut())
                    || user_date_fin_pret.isAfter(adh_abonnement.getDateFin())) {
                throw new Exception("Le prêt n'est pas compris dans la période de l'abonnement. "
                        + "Abonnement valide du " + adh_abonnement.getDateDebut() + " au "
                        + adh_abonnement.getDateFin());
            }
            pret.setDateFin(prolongement.getNewDateFin());
            HistoStatut histoStatut = new HistoStatut(
                    null,
                    statut,
                    LocalDate.now(),
                    null,
                    null,
                    null,
                    null,
                    prolongement);
            histoStatutService.save(histoStatut);
            pretService.save(pret);
        }
    }
    public void saveByPret (Pret pret) throws Exception 
    {
        if  (!getByPret(pret).isEmpty()) {
            
            throw new Exception("Vous avez deja prolonger ou demander sur ce pret");
        }
        Utilisateur adherant = pret.getAdherant();
        Quota user_quota = quotaService.getByCategorieAdherant(adherant.getCategorie());
        LocalDate last_date_fin = pret.getDateDebut();
        LocalDate new_date_fin  =  last_date_fin.plusDays(user_quota.getNombreJour());
        LocalDate now_date  =  LocalDate.now();

        
        Prolongement prolongement = new Prolongement(null, pret, last_date_fin, new_date_fin, now_date, null);
        Statut new_statut = statutService.getByLibelle("En attente");
        HistoStatut new_histo_statut = new HistoStatut(null, new_statut, now_date, null, adherant, null, null, prolongement);
        repository.save(prolongement);
        histoStatutService.save(new_histo_statut);

    }
    public List<Prolongement> getByPret(Pret pret) {
        return this.repository.findByPret(pret);
    }
    public List<Prolongement> getAll() {
        return repository.findAll();
    }
}