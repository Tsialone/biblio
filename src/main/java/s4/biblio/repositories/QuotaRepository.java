package s4.biblio.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import s4.biblio.models.Abonnement;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Quota;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Integer> {
    Quota findByCategorieAdherant(Categorie categorie);
}