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
import s4.biblio.services.UtilisateurService;

@Controller
@RequestMapping("/abonnement")
public class AbonnementController {

    private final AbonnementService abonnementService;

    @Autowired
    private UtilisateurService utilisateurService;

    AbonnementController(AbonnementService abonnementService) {
        this.abonnementService = abonnementService;
    }

    @GetMapping("form")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("content", "pages/create/form_abonnement.jsp");
        mv.addObject("title", "Abonnement");
        mv.addObject("fonctionality", "Abonnement");
        mv.addObject("all_utilisateurs", utilisateurService.getAll());
        return mv;
    }

    @PostMapping("save")
    public String saveAbonnement(@ModelAttribute AbonnementForm form, RedirectAttributes redirectAttributes) {
        System.out.println(form);
        try {
            abonnementService.saveByForm(form);
            redirectAttributes.addFlashAttribute("message", "Merci!");
            redirectAttributes.addFlashAttribute("message_type", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Erreur: " + e.getMessage());
            redirectAttributes.addFlashAttribute("message_type", "danger");
        }
        return "redirect:/abonnement/form";

    }
}
