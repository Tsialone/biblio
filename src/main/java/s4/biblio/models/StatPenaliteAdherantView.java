package s4.biblio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "vue_stat_penalite_par_adherant")
@Getter
@Setter
public class StatPenaliteAdherantView {
    
    @Id
    private Integer adherantId;

    private String nomAdherant;
    private String prenomAdherant;
    private Long totalPenalites;

}
