package s4.biblio.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s4.biblio.models.Categorie;
import s4.biblio.models.HistoStatut;
import s4.biblio.models.Pret;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UtilisateurForm {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String tel;
    private String adresse;
    private Categorie categorie;
    private LocalDate dateNaissance;
    private List<Pret> prets= new ArrayList<>();
    private List<HistoStatut> histoStatuts = new ArrayList<>();


    // private List<HistoStatut> 
    // @Override
    // public String toString (){
    //     return id;
    // }


    

}
