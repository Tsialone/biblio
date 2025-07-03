package s4.biblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.form.ReservationForm;
import s4.biblio.models.Abonnement;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Pret;
import s4.biblio.models.Quota;
import s4.biblio.models.Reservation;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private QuotaService quotaService;
    @Autowired
    private StatutService statutService;
    @Autowired
    private HistoStatutService histoStatutService;
    @Autowired
    private AbonnementService abonnementService;
    @Autowired
    private PretService pretService;

    public void updateStatutReservation(Statut statut, Reservation reservation) throws Exception {
        if (statut.getLibelle().equalsIgnoreCase("Annulée")) {
            // si la reservation est deja valider
            if (!reservation.getStatut().getLibelle().equalsIgnoreCase("En attente")) {
                throw new Exception("Transaction deja close");
            }
            HistoStatut histoStatut = new HistoStatut(null, statut, null, null, null, reservation, null, null);
            histoStatutService.save(histoStatut);
        } else if (statut.getLibelle().equalsIgnoreCase("Confirmée")) {
            Utilisateur user = reservation.getAdherant();
            Statut user_statut = user.getStatut();
            Abonnement adh_abonnement = abonnementService.getByAdherantDate(reservation.getDateDebut(), user);

            // si la reservation est deja valider
            if (!reservation.getStatut().getLibelle().equalsIgnoreCase("En attente")) {
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

            LocalDate user_date_debut_pret = reservation.getDateDebut();
            LocalDate user_date_fin_pret = reservation.getDateFin();

            if (reservation.getCategoriePret().getLibelle().equalsIgnoreCase("Sur place")) {
                user_date_fin_pret = reservation.getDateDebut();
            }
            Pret exemplaire_prete_dispo = pretService.estDispo(reservation.getExemplaire(), user_date_debut_pret,
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
            Pret pret = new Pret(
                    null,
                    reservation.getExemplaire(),
                    user, user_date_debut_pret,
                    user_date_fin_pret,
                    reservation.getCategoriePret(),
                    null,
                    null);
            HistoStatut histoStatut = new HistoStatut(
                    null,
                    statut,
                    LocalDate.now(),
                    null,
                    null,
                    reservation,
                    null,
                    null);
            histoStatutService.save(histoStatut);
            pretService.save(pret);

        }
    }

    public Optional<Reservation> getById(Integer id) {
        return repository.findById(id);
    }

    public List<Reservation> getByAdherant(Utilisateur adherant) {
        return repository.findByAdherant(adherant);
    }

    public List<Reservation> getAll() {
        return repository.findAll();
    }

    public void saveByForm(ReservationForm form, Utilisateur utilisateur) {
        Quota quota = quotaService.getByCategorieAdherant(utilisateur.getCategorie());
        int nbr_jour_possible = quota.getNombreJour();
        Statut statut = statutService.getByLibelle("En attente");

        Reservation reservation = new Reservation(null,
                form.getExemplaire(),
                utilisateur,
                form.getDateDebut(),
                null,
                form.getCategoriePret(),
                null);

        reservation.setDateFin(reservation.getDateDebut().plusDays(nbr_jour_possible));
        if (reservation.getCategoriePret().getLibelle().equalsIgnoreCase("Sur place")) {
            reservation.setDateFin(reservation.getDateDebut());
        }
        repository.save(reservation);
        HistoStatut histoStatut = new HistoStatut(null, statut, form.getDateDebut(), null, null, reservation, null,
                null);
        histoStatutService.save(histoStatut);

    }

    // public List<Categorie> getByType (E_TypeCategorie type){
    // return repository.findByType(type);
    // }
}