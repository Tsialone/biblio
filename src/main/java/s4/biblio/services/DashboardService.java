package s4.biblio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s4.biblio.models.StatPenaliteAdherantView;
import s4.biblio.repositories.StatPenaliteAdherantViewRepository;
import s4.biblio.repositories.VExemplaireEmpruntRepository;
import s4.biblio.views.VExemplaireEmprunt;

@Service
public class DashboardService {
    @Autowired
    private VExemplaireEmpruntRepository vExemplaireEmpruntRepository;

    @Autowired
    private StatPenaliteAdherantViewRepository  statPenaliteAdherantViewRepository;

    public List<VExemplaireEmprunt> getStatsExemplaires() {
        return vExemplaireEmpruntRepository.findAll();
    }
    public List<StatPenaliteAdherantView> adherantPenaliteStat () {
        return statPenaliteAdherantViewRepository.findAll();
    }
}
