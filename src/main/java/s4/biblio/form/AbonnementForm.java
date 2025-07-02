package s4.biblio.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s4.biblio.models.Utilisateur;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AbonnementForm {
    private Integer id;
    private Utilisateur adherant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
