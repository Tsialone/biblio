package s4.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Pret;
import s4.biblio.models.Remise;

@Repository
public interface RemiseRepository extends JpaRepository<Remise, Integer> {
    Remise  findByPret(Pret pret);
}