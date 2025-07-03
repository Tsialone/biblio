package s4.biblio.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import s4.biblio.models.Abonnement;
import s4.biblio.models.Pret;
import s4.biblio.models.Prolongement;
import s4.biblio.models.Utilisateur;


@Repository
public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {
    List<Prolongement> findByPretAdherant (Utilisateur adherant);
    List<Prolongement> findByPret (Pret pret);

}