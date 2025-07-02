package s4.biblio.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;





@Repository
public interface HistoStatutRepository extends JpaRepository<HistoStatut, Integer> {


    @Query("SELECT hs FROM HistoStatut hs WHERE " +
                 "(hs.utilisateur = :utilisateur ) ORDER BY date_debut desc"
                 
        )
    List<HistoStatut> findByUtilisateur(@Param("utilisateur") Utilisateur adherant);
}