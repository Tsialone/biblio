package s4.biblio.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import s4.biblio.form.UtilisateurForm;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Utilisateur;
import s4.biblio.services.CategorieService;
import s4.biblio.services.UtilisateurService;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    private final CategorieService categorieService;

    public UtilisateurController(UtilisateurService utilisateurService, CategorieService categorieService) {
        this.utilisateurService = utilisateurService;
        this.categorieService = categorieService;
    }

    @GetMapping("login")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("pages/login");

        // mv.addObject("content", "pages/views/user_home.jsp");
        // mv.addObject("title", "home");
        // mv.addObject("fonctionality", "home");

        return mv;
    }

    @PostMapping("login/verify")
    public ModelAndView verifyLogin() {
        ModelAndView mv = new ModelAndView("pages/login");

        return mv;
    }

    @GetMapping("inscription")
    public ModelAndView inscription() {
        ModelAndView mv = new ModelAndView("pages/inscription");
        List<Categorie> categorie_utilisateur = categorieService.getByType(E_TypeCategorie.adherant);
        mv.addObject("categorie_utilisateur", categorie_utilisateur);
        // mv.addObject("content", "pages/views/user_home.jsp");
        // mv.addObject("title", "home");
        // mv.addObject("fonctionality", "home");

        return mv;
    }

    @PostMapping("inscription/save")
    public String inscriptionSave(@ModelAttribute UtilisateurForm form, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("pages/inscription");
        System.out.println(form);
        try {
            utilisateurService.saveByForm(form);
            redirectAttributes.addFlashAttribute("message", "Inscription reussi 👌");
            redirectAttributes.addFlashAttribute("message_type", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Erreur: " + e.getMessage());
            redirectAttributes.addFlashAttribute("message_type", "danger");
            // e.printStackTrace();
        }
        List<Categorie> categorie_utilisateur = categorieService.getByType(E_TypeCategorie.adherant);
        mv.addObject("categorie_utilisateur", categorie_utilisateur);
        // return mv;
        return "redirect:/utilisateur/inscription";
    }
}
