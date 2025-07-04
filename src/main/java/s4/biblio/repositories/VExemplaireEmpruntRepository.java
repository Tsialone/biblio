package s4.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.biblio.views.VExemplaireEmprunt;

public interface VExemplaireEmpruntRepository extends JpaRepository<VExemplaireEmprunt, Integer> {
}
