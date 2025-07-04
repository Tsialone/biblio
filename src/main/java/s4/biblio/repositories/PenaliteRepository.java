package s4.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Penalite;
import s4.biblio.models.Pret;
import s4.biblio.models.Utilisateur;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {

    List<Penalite> findByPretAdherant (Utilisateur adherant);
     @Query(value = """
        SELECT COUNT(*) 
        FROM utilisateur u 
        LEFT JOIN pret p ON p.id_adherant = u.id 
        LEFT JOIN penalite pen ON pen.id_pret = p.id 
        WHERE pen.id IS NOT NULL
    """, nativeQuery = true)
    int countUtilisateursAvecPenalite();

    @Query(value = """
        SELECT AVG(DATEDIFF(penalite.date_fin, penalite.date_debut)) 
        FROM penalite 
        WHERE date_fin IS NOT NULL AND date_debut IS NOT NULL
    """, nativeQuery = true)
    Double getMoyenneDureePenaliteEnJours();

    Penalite findByPret (Pret  pret);
}