package s4.biblio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @GetMapping("login")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("pages/login");

        // mv.addObject("content", "pages/views/user_home.jsp");
        // mv.addObject("title", "home");
        // mv.addObject("fonctionality", "home");


        return mv;
    }

    @PostMapping("verify")
    public ModelAndView verifyLogin() {
        ModelAndView mv = new ModelAndView("pages/login");

        // mv.addObject("content", "pages/views/user_home.jsp");
        // mv.addObject("title", "home");
        // mv.addObject("fonctionality", "home");
        System.out.println("ello");


        return mv;
    }
}
