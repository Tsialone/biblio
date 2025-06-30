package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret")
    private Pret pret;
    
    private Date date_demande;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_histo_statut")
    private HistoStatut histoStatut;
}