package s4.biblio.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "jour_ferie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JourFerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Date date;
    private String description;
}