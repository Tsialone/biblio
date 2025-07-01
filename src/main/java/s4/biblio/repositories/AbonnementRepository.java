package s4.biblio.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import s4.biblio.models.Abonnement;
import s4.biblio.models.Utilisateur;


@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    @Query(
        "SELECT a FROM Abonnement a WHERE " +
        "(a.dateDebut >= :now_date ) AND  " +
        "(a.dateFin <= :now_date) AND " +
        "(a.adherant = :adherant )"
    )
    Abonnement findByAdherantDate(@Param("now_date") LocalDate debut, @Param("adherant") Utilisateur adherant);

    List<Abonnement> findByAdherant (Utilisateur adherant);
}