package s4.biblio.services;


import org.springframework.stereotype.Service;

import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.UtilisateurRepository;

import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository repository;

    public UtilisateurService(UtilisateurRepository repository) {
        this.repository = repository;
    }
    public List<Utilisateur> getAll() {
        return repository.findAll();
    }
}