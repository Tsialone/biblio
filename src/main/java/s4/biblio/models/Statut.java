package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "statut")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String libelle;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeStatut type;
    
    @OneToMany(mappedBy = "statut", fetch = FetchType.LAZY)
    private List<HistoStatut> histoStatuts;
    
    public enum TypeStatut {
        exemplaire, adherant, pret, reservation
    }
}