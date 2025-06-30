package s4.biblio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestContoller {

    @GetMapping("test")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("content", "pages/views/user_home.jsp");
        mv.addObject("title", "home");
        mv.addObject("fonctionality", "home");


        System.out.println("xxxx");
        return mv;
    }
}
