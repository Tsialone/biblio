package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "livre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String titre;
    private Date date_publication;
    private String description;
    
    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    private List<Exemplaire> exemplaires;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "livre_auteur",
        joinColumns = @JoinColumn(name = "id_livre"),
        inverseJoinColumns = @JoinColumn(name = "id_auteur")
    )
    private List<Auteur> auteurs;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "livre_categorie",
        joinColumns = @JoinColumn(name = "id_livre"),
        inverseJoinColumns = @JoinColumn(name = "id_categorie")
    )
    private List<Categorie> categories;
}