package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String adresse;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
    
    
    // @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    // private List<Abonnement> abonnements;
    
    @OneToMany(mappedBy = "adherant", fetch = FetchType.LAZY)
    private List<Pret> prets;

    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private List<HistoStatut> histoStatuts;
}