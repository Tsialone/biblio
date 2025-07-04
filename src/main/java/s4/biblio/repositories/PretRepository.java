package s4.biblio.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.Exemplaire;
import s4.biblio.models.Pret;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;





@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
    List<Pret> findByExemplaire(Exemplaire exemplaire);
    List<Pret> findByAdherant(Utilisateur adherant);
    @Query("SELECT AVG(DATEDIFF(p.dateFin, p.dateDebut)) FROM Pret p")
    Double getDureeMoyennePret();

}