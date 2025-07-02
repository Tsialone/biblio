package s4.biblio.services;


import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.form.AbonnementForm;
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

    public void saveByForm  (AbonnementForm abonnementForm) throws Exception {
        Abonnement abonnement = new Abonnement(
            null , 
            abonnementForm.getAdherant() ,
            abonnementForm.getDateDebut() , 
            abonnementForm.getDateFin()
        );
        if  (!abonnement.getDateFin().isAfter(abonnement.getDateDebut())) {
            throw new Exception("Date fin doit etre apres de la date debut");
        }
        LocalDate now_date = LocalDate.now();
        Abonnement abonnement_now_date = getByAdherantDate(now_date, abonnement.getAdherant());
        System.out.println("debugggg " + abonnement_now_date);
        if (abonnement_now_date!= null){
            throw new Exception("Vous avez deja fait cette abonnement");
        }
        this.repository.save(abonnement);
    }


    public List<Abonnement> getAll() {
        return repository.findAll();
    }
    public Abonnement getByAdherantDate (LocalDate now_date , Utilisateur adherant) {
        List<Abonnement> abonnements = this.repository.findByAdherantDate (now_date ,  adherant);
        if (abonnements.isEmpty()) {
            return null;
        }
        return abonnements.getFirst();
    }   

    public List<Abonnement> getByAdherant (Utilisateur utilisateur){
        return this.repository.findByAdherant(utilisateur);
    }
   
}