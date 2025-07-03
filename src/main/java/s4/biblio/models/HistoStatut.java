package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "histo_statut")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoStatut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_statut")
    private Statut statut;
    
    @Column(name = "date_debut")
    private LocalDate dateDebut;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;

    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur" )
    private Utilisateur utilisateur;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_pret")
    // private Pret pret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_penalite")
    private Penalite penalite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prolongement")
    private Prolongement prolongement;

}