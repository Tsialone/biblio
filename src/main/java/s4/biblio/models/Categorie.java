package s4.biblio.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String libelle;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeCategorie type;
    
    private String description;
    

    public enum TypeCategorie {
        livre, adherant, admin, pret
    }
    @OneToMany(mappedBy = "categorieAdherant", fetch = FetchType.LAZY)
    private List<Quota> quotas;

}