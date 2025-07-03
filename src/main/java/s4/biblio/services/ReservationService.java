package s4.biblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.form.ReservationForm;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Quota;
import s4.biblio.models.Reservation;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

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

    public List<Reservation> getByAdherant (Utilisateur adherant) {
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
                null);
        reservation.setDateFin(reservation.getDateDebut().plusDays(nbr_jour_possible));
        repository.save(reservation);
        HistoStatut histoStatut = new HistoStatut(null, statut, form.getDateDebut(), null, null, reservation, null);
        histoStatutService.save(histoStatut);

    }

    // public List<Categorie> getByType (E_TypeCategorie type){
    // return repository.findByType(type);
    // }
}