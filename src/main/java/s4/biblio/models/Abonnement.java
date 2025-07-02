package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "abonnement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherant")
    private Utilisateur adherant;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_categorie")
    // private Categorie categorie;
    

    private LocalDate dateDebut;
    private LocalDate dateFin;

    
}