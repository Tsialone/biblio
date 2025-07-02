package s4.biblio.services;


import org.springframework.stereotype.Service;

import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.Statut;
import s4.biblio.repositories.StatutRepository;
import java.util.List;

@Service
public class StatutService {
    private final StatutRepository repository;

    public StatutService(StatutRepository repository) {
        this.repository = repository;
    }
    public List<Statut> getAll() {
        return repository.findAll();
    }
    public List<Statut> getByType (E_TypeStatut type_statut){
        return this.repository.findByType(type_statut);
    }
    public Statut getByLibelle (String libelle){
        return this.repository.findByLibelle(libelle);
    }
    public Statut getByTypeAndLibelle (E_TypeStatut type , String libelle){
        return this.repository.findByTypeAndLibelle(type, libelle);
    }
}