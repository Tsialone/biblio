package s4.biblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.JourFerie;
import s4.biblio.repositories.CategorieRepository;
import s4.biblio.repositories.JourFerieRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class JourFerieService {
    @Autowired
    private JourFerieRepository repository;

    public List<JourFerie> getAll() {
        return repository.findAll();
    }

    public Integer nbrSautByFerieAndDate(LocalDate fin) {
        int count = 0;
        LocalDate currentDate = fin;

        for (;;) {
            boolean isFerie = false;

            for (JourFerie jourFerie : getAll()) {
                LocalDate jourFerieDate = LocalDate.of(currentDate.getYear(), jourFerie.getMois(), jourFerie.getJour());
                if (currentDate.isEqual(jourFerieDate)) {
                    isFerie = true;
                    break;
                }
            }
            if (isFerie) {
                count++;
                currentDate = currentDate.plusDays(1);
            } else {
                break;
            }
        }
        return count;
    }

}