package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pret")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherant")
    private Utilisateur adherant;
    
    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
    
    // @OneToMany(mappedBy = "pret", fetch = FetchType.LAZY)
    // private List<Reservation> reservations;
    
    @OneToMany(mappedBy = "pret", fetch = FetchType.LAZY)
    private List<Penalite> penalites;

    // @OneToMany(mappedBy = "pret", fetch = FetchType.LAZY)
    // private List<HistoStatut> histoStatuts;
}