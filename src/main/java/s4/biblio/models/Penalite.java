package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "penalite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret")
    private Pret pret;
    
    private Date date_debut;
    private Integer quotas;
    private Integer nbr_jour;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_histo_statut")
    private HistoStatut histoStatut;
}