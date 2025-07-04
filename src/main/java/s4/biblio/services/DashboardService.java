package s4.biblio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s4.biblio.repositories.VExemplaireEmpruntRepository;
import s4.biblio.views.VExemplaireEmprunt;

@Service
public class DashboardService {
    @Autowired
    private VExemplaireEmpruntRepository vExemplaireEmpruntRepository;

    public List<VExemplaireEmprunt> getStatsExemplaires() {
        return vExemplaireEmpruntRepository.findAll();
    }
}
