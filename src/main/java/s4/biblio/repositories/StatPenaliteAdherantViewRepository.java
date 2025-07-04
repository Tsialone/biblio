package s4.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4.biblio.models.StatPenaliteAdherantView;


@Repository
public interface StatPenaliteAdherantViewRepository extends JpaRepository<StatPenaliteAdherantView, Integer> {
    List<StatPenaliteAdherantView> findAll();
}
