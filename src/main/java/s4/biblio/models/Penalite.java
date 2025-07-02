package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "penalite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret")
    private Pret pret;
    
    private Date date_debut;
    private Integer quotas;
    private Integer nbr_jour;
    
   @OneToMany(mappedBy = "penalite", fetch = FetchType.LAZY)
    private List<HistoStatut> histoStatuts;
}