package s4.biblio.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Quota;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.QuotaRepository;

import java.util.List;

@Service
@Transactional
public class QuotaService {
    @Autowired
    private  QuotaRepository repository;

   
    public List<Quota> getAll() {
        return repository.findAll();
    }
   
    public  Quota getByCategorieAdherantType(E_TypeCategorie type){
        return this.repository.findByCategorieAdherantType(type);
    };

}