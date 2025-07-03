package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    private Categorie categoriePret;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
    private List<HistoStatut> histoStatuts;

    public Statut getStatut() {
        if (!histoStatuts.isEmpty()) {
            List<HistoStatut> trie  = histoStatuts.stream()
            .sorted(Comparator.comparing(HistoStatut::getId).reversed())
            .collect(Collectors.toList());
            return trie.getFirst().getStatut();
        }
        return null;
    }

}