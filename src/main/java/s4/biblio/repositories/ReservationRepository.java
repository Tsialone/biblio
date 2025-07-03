package s4.biblio.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.Exemplaire;
import s4.biblio.models.Pret;
import s4.biblio.models.Reservation;
import s4.biblio.models.Statut;





@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}