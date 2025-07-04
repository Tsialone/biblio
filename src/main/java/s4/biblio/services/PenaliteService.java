package s4.biblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Penalite;
import s4.biblio.models.Pret;
import s4.biblio.models.Utilisateur;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.PenaliteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PenaliteService {
    @Autowired
    private PenaliteRepository repository;

    public Penalite getByPret (Pret pret) {
        return repository.findByPret(pret);
    }
    public Double getMoyenneDureePenaliteEnJours () {
        return repository.getMoyenneDureePenaliteEnJours();
    }
    public int  countUtilisateursAvecPenalite (){
        return repository.countUtilisateursAvecPenalite();
    }
    public Penalite estDansUnePenalite(LocalDate debut, LocalDate fin, Utilisateur utilisateur) {
        for (Penalite penalite : getAll()) {
            if (penalite.getPret().getAdherant().getId() == utilisateur.getId()) {
                if (PretService.chevauchent(debut, fin, penalite.getDateDebut(), penalite.getDateFin())) {
                    return penalite;
                }
            }

        }
        return null;
    }

    public List<LocalDate> getIntervallePenaliteByAdherant(Utilisateur adherant) {
        List<Penalite> penalites = getByPretAdherant(adherant);

        if (penalites == null || penalites.isEmpty()) {
            return new ArrayList<>(); // Aucun intervalle
        }

        LocalDate dateMin = penalites.stream()
                .map(Penalite::getDateDebut)
                .min(LocalDate::compareTo)
                .orElse(null);

        LocalDate dateMax = penalites.stream()
                .map(Penalite::getDateFin)
                .max(LocalDate::compareTo)
                .orElse(null);

        List<LocalDate> intervalle = new ArrayList<>();
        intervalle.add(dateMin);
        intervalle.add(dateMax);

        return intervalle;
    }

    public List<Penalite> getAll() {
        return repository.findAll();
    }

    public List<Penalite> getByPretAdherant(Utilisateur utilisateur) {
        return repository.findByPretAdherant(utilisateur);
    }

    public void save(Penalite penalite) {
        repository.save(penalite);
    }
}