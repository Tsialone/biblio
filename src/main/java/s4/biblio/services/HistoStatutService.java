package s4.biblio.services;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.HistoStatutRepository;

import java.util.List;

@Service
public class HistoStatutService {
    private final HistoStatutRepository repository;

    public HistoStatutService(HistoStatutRepository repository) {
        this.repository = repository;
    }
    public List<HistoStatut> getAll() {
        return repository.findAll();
    }
    public void save  (HistoStatut histoStatut) {
        this.repository.save(histoStatut);
    }
    public List<HistoStatut> getByUtilisateur (Utilisateur utilisateur) {
        return repository.findByUtilisateur(utilisateur);
    }
    public HistoStatut getCurrentByUtilisateur (Utilisateur utilisateur) {
        if (getByUtilisateur(utilisateur).isEmpty()) {
            return null;
        }
        return getByUtilisateur(utilisateur).getFirst();
    }
   
}