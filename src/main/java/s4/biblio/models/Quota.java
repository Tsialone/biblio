package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie_adherant", nullable = false)
    private Categorie categorieAdherant;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie_abonnement", nullable = false)
    private Categorie categorieAbonnement;

    @Column(name = "nbr_livre", nullable = false)
    private Integer nombreLivres;

    @Column(name = "nbr_jour", nullable = false)
    private Integer nombreJour;
}