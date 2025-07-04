package s4.biblio.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Pret;
import s4.biblio.models.Remise;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.RemiseRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RemiseService {
    @Autowired
    private  RemiseRepository repository;

    
    public List<Remise> getAll() {
        return repository.findAll();
    }
    public void save   (Remise remise) {
        repository.save(remise);
    }
    public Remise getByPret (Pret pret) {
        return repository.findByPret(pret);
    }
    
}