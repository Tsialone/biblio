package s4.biblio.views;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import s4.biblio.models.E_Abonnement;


@Getter
@Setter
@ToString
public class V_Abonnement 
{
    LocalDate dateDebut;
    LocalDate dateFin;
    E_Abonnement etat;
}
