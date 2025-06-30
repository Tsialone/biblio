package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exemplaire")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livre")
    private Livre livre;
    
    private Date date_acquisition;
    
    @OneToMany(mappedBy = "exemplaire", fetch = FetchType.LAZY)
    private List<Pret> prets;

    @OneToMany(mappedBy = "exemplaire", fetch = FetchType.LAZY)
    private List<HistoStatut> histoStatuts;

}