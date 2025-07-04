package s4.biblio.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_exemplaire_emprunt")
public class VExemplaireEmprunt {

    @Id
    private Integer id_exemplaire;
    private Integer nbr_emprunt;

    // Getters et Setters
    public Integer getId_exemplaire() {
        return id_exemplaire;
    }

    public void setId_exemplaire(Integer id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    public Integer getNbr_emprunt() {
        return nbr_emprunt;
    }

    public void setNbr_emprunt(Integer nbr_emprunt) {
        this.nbr_emprunt = nbr_emprunt;
    }
}
