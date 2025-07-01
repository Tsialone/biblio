package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "abonement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie_utilisateur")
    private Categorie categorieUtilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
    

    private Date date_debut;
    private Date date_fin;
}