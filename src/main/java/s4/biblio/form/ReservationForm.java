package s4.biblio.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s4.biblio.models.Categorie;
import s4.biblio.models.Exemplaire;

import java.time.LocalDate;
import java.util.Locale.Category;


@Getter
@Setter
@ToString
public class ReservationForm {
    private Integer id;
    private Exemplaire exemplaire;
    private LocalDate dateDebut;
    private Categorie categoriePret;
}
