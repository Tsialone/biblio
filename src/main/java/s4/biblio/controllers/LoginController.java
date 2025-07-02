package s4.biblio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("pages/login");
        return mv;
    }
}
