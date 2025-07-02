package s4.biblio.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.models.Exemplaire;
import s4.biblio.repositories.ExemplaireRepository;

import java.util.List;

@Service
@Transactional
public class ExemplaireService {
    @Autowired
    private  ExemplaireRepository repository;

    
    public List<Exemplaire> getAll() {
        return repository.findAll();
    }
}