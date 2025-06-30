package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "auteur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nom;
    private String prenom;
    
    @ManyToMany(mappedBy = "auteurs", fetch = FetchType.LAZY)
    private List<Livre> livres;
}