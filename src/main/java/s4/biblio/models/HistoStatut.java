package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

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
    
    private Date date_debut;
    
    @OneToOne(mappedBy = "histoStatut", fetch = FetchType.LAZY)
    private Exemplaire exemplaire;
    
    @OneToMany(mappedBy = "histoStatut", fetch = FetchType.LAZY)
    private List<Pret> prets;
    
    @OneToMany(mappedBy = "histoStatut", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
    
    @OneToMany(mappedBy = "histoStatut", fetch = FetchType.LAZY)
    private List<Penalite> penalites;
}