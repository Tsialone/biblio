package s4.biblio.controllers;

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
import s4.biblio.models.Utilisateur;
import s4.biblio.services.AbonnementService;
import s4.biblio.services.CategorieService;
import s4.biblio.services.DashboardService;
import s4.biblio.services.UtilisateurService;
import s4.biblio.views.VExemplaireEmprunt;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

   
    @Autowired
    private DashboardService dashboardService;


    @GetMapping("stats")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("content", "pages/views/stats.jsp");
        mv.addObject("title", "Abonnement");
        mv.addObject("fonctionality", "Statistique");
        // List<VExemplaireEmprunt> exemplaires = vExemplaireEmpruntRepository.findAll();
        mv.addObject("exemplaires", dashboardService.getStatsExemplaires());
        // mv.addObject("all_utilisateurs", utilisateurService.getAll());
        return mv;
    }
}
