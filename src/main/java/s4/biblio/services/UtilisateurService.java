package s4.biblio.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import s4.biblio.form.UtilisateurForm;
import s4.biblio.models.Abonnement;
import s4.biblio.models.E_Abonnement;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.UtilisateurRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UtilisateurService {
    private final UtilisateurRepository repository;
    private final StatutService statutService;
    private final HistoStatutService histoStatutService;
    private final AbonnementService abonnementService;




    public UtilisateurService(UtilisateurRepository repository , StatutService statutService , HistoStatutService histoStatutService  ,AbonnementService abonnementService) {
        this.repository = repository;
        this.statutService = statutService;
        this.histoStatutService = histoStatutService;
        this.abonnementService = abonnementService;
    }
    public List<Utilisateur> getAll() {
        return repository.findAll();
    }
    public void saveByForm (UtilisateurForm form) throws Exception{
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(form.getNom());
        utilisateur.setPrenom(form.getPrenom());
        utilisateur.setEmail(form.getEmail());
        utilisateur.setMdp(form.getMdp());
        utilisateur.setTel(form.getTel());
        utilisateur.setAdresse(form.getAdresse());
        utilisateur.setCategorie(form.getCategorie());
        utilisateur.setPrets(form.getPrets());
        utilisateur.setDateNaissance(form.getDateNaissance());

        Statut statut = this.statutService.getByTypeAndLibelle( E_TypeStatut.adherant  ,"Actif");
       if  (statut == null) {
           throw new Exception("Statut ne doit pas etre null apres inscription");
       }
       if (this.getByEmail(utilisateur.getEmail()) != null) {
           throw new Exception("Email deja utiliser!");
       }
        HistoStatut histoStatut = new HistoStatut(null, statut, LocalDate.now(), null, utilisateur, null, null, null);
        this.repository.save(utilisateur);
        this.histoStatutService.save(histoStatut);
    }
    public Utilisateur  getByEmailAndMdp(String email  , String mdp){
        return this.repository.findByEmailAndMdp(email, mdp);
    }
    public Utilisateur getByEmail (String email) {
        return this.repository.findByEmail(email);
    }

    public E_Abonnement getEtatAbonnement (Utilisateur utilisateur) {
        List<Abonnement> his_abonnements =   abonnementService.getByAdherant(utilisateur);
        Abonnement current_abonnement = abonnementService.getByAdherantDate(LocalDate.now(), utilisateur);
        if (his_abonnements.isEmpty()) {
            return E_Abonnement.non_abonne;
        }
        if (current_abonnement == null) {
            return E_Abonnement.expire;
        }
        return E_Abonnement.en_cours;
    } 

    public List<Utilisateur> getByCategorieType (E_TypeCategorie e_TypeCategorie) {
        return repository.findByCategorieType(e_TypeCategorie);
    }  
}