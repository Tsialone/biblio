package s4.biblio.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s4.biblio.models.Exemplaire;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class ReservationForm {
    private Integer id;
    private Exemplaire exemplaire;
    private LocalDate dateDebut;
}
