package s4.biblio.form;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s4.biblio.models.Categorie;
import s4.biblio.models.Exemplaire;
import s4.biblio.models.Utilisateur;

@Getter
@Setter
@ToString
public class PretForm {
    Exemplaire exemplaire;
    Utilisateur adherant;
    LocalDate dateDebut;
    Categorie categorie;


}
