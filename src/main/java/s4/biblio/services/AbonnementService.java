package s4.biblio.services;


import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.models.Abonnement;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.AbonnementRepository;
import s4.biblio.repositories.CategorieRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AbonnementService {
    private final AbonnementRepository repository;

    public AbonnementService(AbonnementRepository repository) {
        this.repository = repository;
    }
    public List<Abonnement> getAll() {
        return repository.findAll();
    }
    public Abonnement getByAdherantDate (LocalDate now_date , Utilisateur adherant) {
        return this.repository.findByAdherantDate (now_date ,  adherant);
    }   

    public List<Abonnement> getByAdherant (Utilisateur utilisateur){
        return this.repository.findByAdherant(utilisateur);
    }
   
}