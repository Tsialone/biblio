package s4.biblio.services;


import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.repositories.CategorieRepository;

import java.util.List;

@Service
@Transactional
public class CategorieService {
    private final CategorieRepository repository;

    public CategorieService(CategorieRepository repository) {
        this.repository = repository;
    }
    public List<Categorie> getAll() {
        return repository.findAll();
    }
    public List<Categorie> getByType (E_TypeCategorie type){
        return repository.findByType(type);
    }
}