package s4.biblio.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import s4.biblio.form.UtilisateurForm;
import s4.biblio.models.Abonnement;
import s4.biblio.models.Categorie;
import s4.biblio.models.E_Abonnement;
import s4.biblio.models.E_TypeCategorie;
import s4.biblio.models.Utilisateur;
import s4.biblio.services.AbonnementService;
import s4.biblio.services.CategorieService;
import s4.biblio.services.UtilisateurService;
import s4.biblio.views.V_Abonnement;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    private final CategorieService categorieService;
    private final AbonnementService abonnementService;


    public UtilisateurController(UtilisateurService utilisateurService, CategorieService categorieService , AbonnementService abonnementService) {
        this.utilisateurService = utilisateurService;
        this.categorieService = categorieService;
        this.abonnementService = abonnementService;
    }

    @GetMapping("home")
    public ModelAndView getHome(HttpSession session) {
        ModelAndView mv = new ModelAndView("layout");
        Utilisateur utilisateur =(Utilisateur) session.getAttribute("utilisateur");
        mv.addObject("content", "pages/home.jsp");
        mv.addObject("title", "Home");
        mv.addObject("fonctionality", "Home");
        List<V_Abonnement> all_v_abonnement = new ArrayList<>();
        LocalDate now_date = LocalDate.now();
        for (Abonnement abonnement : abonnementService.getByAdherant(utilisateur)) {
            V_Abonnement v_abonnement  = new V_Abonnement();
            v_abonnement.setDateDebut(abonnement.getDateDebut());
            v_abonnement.setDateFin(abonnement.getDateFin());
            if ((now_date.isAfter(abonnement.getDateDebut()) || now_date.isEqual(abonnement.getDateDebut())) &&( now_date.isBefore(abonnement.getDateFin()) || now_date.isEqual(abonnement.getDateFin()) )) {
                v_abonnement.setEtat(E_Abonnement.en_cours);
            }
            else 
            {
                v_abonnement.setEtat(E_Abonnement.expire);
            }
            all_v_abonnement.add(v_abonnement);
        }
        boolean deja_abonne = true;
        if (all_v_abonnement.isEmpty()) {
            deja_abonne = false;
        }
        mv.addObject("all_v_abonnement", all_v_abonnement);
        mv.addObject("deja_abonne", deja_abonne);

        return mv;
    }

    @GetMapping("login")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("pages/login");
        return mv;
    }

    @PostMapping("login/verify")
    public String verifyLogin(@RequestParam("email") String email, @RequestParam("mdp") String mdp,
            RedirectAttributes redirectAttributes, HttpSession session) {

        Utilisateur find = utilisateurService.getByEmailAndMdp(email, mdp);
        if (find == null) {
            redirectAttributes.addFlashAttribute("message", "Mot de passe ou utilisateur non trouver");
            redirectAttributes.addFlashAttribute("message_type", "danger");
            return "redirect:/utilisateur/login";
            
        } else {
            System.out.println(find.getCategorie());
            System.out.println(find.getAbonnements());
            session.setAttribute("utilisateur", find);
            redirectAttributes.addFlashAttribute("message", "Bonjour");
            redirectAttributes.addFlashAttribute("message_type", "success");
        }
        return "redirect:/utilisateur/home";

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
