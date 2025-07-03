package s4.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Penalite;
import s4.biblio.models.Utilisateur;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {
    List<Penalite> findByPretAdherant (Utilisateur adherant);
}