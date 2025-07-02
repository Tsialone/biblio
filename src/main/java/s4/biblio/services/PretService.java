package s4.biblio.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.form.PretForm;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Pret;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.PretRepository;

import java.util.List;

@Service
@Transactional
public class PretService {
    @Autowired
    private  PretRepository repository;

    
    public List<Pret> getAll() {
        return repository.findAll();
    }

    public void  saveByForm (PretForm form){
        Pret pret = new Pret(
            null, 
            form.getExemplaire(), 
            form.getAdherant(), 
            form.getDateDebut(), 
            form.getCategorie(), 
            null, 
            null
            );
        repository.save(pret);
    }
    // public List<Categorie> getByType (E_TypeCategorie type){
    //     return repository.findByType(type);
    // }
}