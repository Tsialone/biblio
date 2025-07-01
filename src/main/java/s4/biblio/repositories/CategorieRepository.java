package s4.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    List<Categorie> findByType(E_TypeCategorie type);
}