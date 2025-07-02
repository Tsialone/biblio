package s4.biblio.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.Statut;





@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer> {
      List<Statut> findByType(E_TypeStatut type_statut);
      Statut findByLibelle (String libelle);
      Statut findByTypeAndLibelle (E_TypeStatut type  , String libelle);
}