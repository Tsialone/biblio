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
import s4.biblio.form.PretForm;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Utilisateur;
import s4.biblio.services.AbonnementService;
import s4.biblio.services.CategorieService;
import s4.biblio.services.ExemplaireService;
import s4.biblio.services.PretService;
import s4.biblio.services.UtilisateurService;

@Controller
@RequestMapping("/pret")
public class PretController {

    
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private  PretService pretService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private CategorieService categorieService;



    @GetMapping("list")
    public ModelAndView getListe(HttpSession session) {
        ModelAndView mv = new ModelAndView("layout");
        Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
        mv.addObject("content", "pages/views/list_prets.jsp");
        mv.addObject("title", "Pret");
        mv.addObject("fonctionality", "Tout mes prets");

        // mv.addObject("utilisateurs", utilisateurService.getByCategorieType(E_TypeCategorie.adherant));
        // mv.addObject("exemplaires", exemplaireService.getAll());
        mv.addObject("prets", pretService.getByAdherant(utilisateur));

        return mv;
    }
    @GetMapping("form")
    public ModelAndView getPretForm() {
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("content", "pages/create/form_pret.jsp");
        mv.addObject("title", "Pret");
        mv.addObject("fonctionality", "Prete un exemplaire de livre");

        mv.addObject("utilisateurs", utilisateurService.getByCategorieType(E_TypeCategorie.adherant));
        mv.addObject("exemplaires", exemplaireService.getAll());
        mv.addObject("categories", categorieService.getByType(E_TypeCategorie.pret));


        return mv;
    }

    @PostMapping("save")
    public String savePret(@ModelAttribute PretForm form, RedirectAttributes redirectAttributes) {
        System.out.println(form);
        try {
            pretService.saveByForm(form);
            redirectAttributes.addFlashAttribute("message", "Merci!");
            redirectAttributes.addFlashAttribute("message_type", "success");
        } catch (Exception e) {
            // e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Erreur: " + e.getMessage());
            redirectAttributes.addFlashAttribute("message_type", "danger");
        }
        return "redirect:/pret/form";

    }
}
