package s4.biblio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import s4.biblio.form.AbonnementForm;
import s4.biblio.models.Pret;
import s4.biblio.models.StatPenaliteAdherantView;
import s4.biblio.models.Utilisateur;
import s4.biblio.services.AbonnementService;
import s4.biblio.services.CategorieService;
import s4.biblio.services.DashboardService;
import s4.biblio.services.PenaliteService;
import s4.biblio.services.PretService;
import s4.biblio.services.UtilisateurService;
import s4.biblio.views.VExemplaireEmprunt;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

   
    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PenaliteService penaliteService;
    @Autowired
    private PretService pretService;
    


    @GetMapping("stats")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("layout");

        // nbr_adherant penalise
        int nbr_adherant_penalise =  penaliteService.countUtilisateursAvecPenalite();
        // duree moyenne des pret
        double duree_moyenne_pret =  pretService.getDureeMoyennePret();
        // duree moyenne des penalites
        double duree_moyenne_penalite =  penaliteService.getMoyenneDureePenaliteEnJours();

        // stat penalite user
        List<StatPenaliteAdherantView> stat_user = dashboardService.adherantPenaliteStat();


        mv.addObject("content", "pages/views/stats.jsp");
        mv.addObject("title", "Abonnement");
        mv.addObject("fonctionality", "Statistique");
        // List<VExemplaireEmprunt> exemplaires = vExemplaireEmpruntRepository.findAll();
        mv.addObject("exemplaires", dashboardService.getStatsExemplaires());
        mv.addObject("nbr_adherant_penalise",nbr_adherant_penalise);
        mv.addObject("duree_moyenne_pret",duree_moyenne_pret);
        mv.addObject("stat_user",stat_user);
        mv.addObject("duree_moyenne_penalite",duree_moyenne_penalite);




        // mv.addObject("all_utilisateurs", utilisateurService.getAll());
        return mv;
    }
}
