package s4.biblio.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Statut;





@Repository
public interface HistoStatutRepository extends JpaRepository<HistoStatut, Integer> {
     
}