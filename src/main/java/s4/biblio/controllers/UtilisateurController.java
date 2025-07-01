package s4.biblio.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import s4.biblio.models.Utilisateur;
import s4.biblio.services.UtilisateurService;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController (UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
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
        List<Utilisateur> all_user = utilisateurService.getAll();
        // for (Utilisateur utilisateur : all_user) {
        //     System.out.println(utilisateur.getEmail());
        // }
        // mv.addObject("content", "pages/views/user_home.jsp");
        // mv.addObject("title", "home");
        // mv.addObject("fonctionality", "home");
        System.out.println("ello");


        return mv;
    }
    @GetMapping("inscription")
    public ModelAndView inscription() {
        ModelAndView mv = new ModelAndView("pages/inscription");
        List<Utilisateur> all_user = utilisateurService.getAll();
        for (Utilisateur utilisateur : all_user) {
            System.out.println(utilisateur.getEmail());
        }
        // mv.addObject("content", "pages/views/user_home.jsp");
        // mv.addObject("title", "home");
        // mv.addObject("fonctionality", "home");
        System.out.println("ello");


        return mv;
    }
}
