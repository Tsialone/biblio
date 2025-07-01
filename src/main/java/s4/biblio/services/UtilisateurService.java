package s4.biblio.services;


import org.springframework.stereotype.Service;

import ch.qos.logback.classic.pattern.Util;
import s4.biblio.form.UtilisateurForm;
import s4.biblio.models.E_TypeStatut;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Statut;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.UtilisateurRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository repository;
    private final StatutService statutService;
    private final HistoStatutService histoStatutService;



    public UtilisateurService(UtilisateurRepository repository , StatutService statutService , HistoStatutService histoStatutService) {
        this.repository = repository;
        this.statutService = statutService;
        this.histoStatutService = histoStatutService;
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
}