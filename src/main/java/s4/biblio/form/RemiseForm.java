package s4.biblio.form;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s4.biblio.models.Categorie;
import s4.biblio.models.Exemplaire;
import s4.biblio.models.Pret;
import s4.biblio.models.Utilisateur;

@Getter
@Setter
@ToString
public class RemiseForm {
    Pret pret;
    LocalDate dateRemise;
}
