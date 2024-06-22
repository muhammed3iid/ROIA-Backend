package ROIA.Units.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean status;
    private String category;
    private String type;
    private long price;
    private String address;
    private int n_bedrooms;
    private int n_bathrooms;
    private int landSpace;
    private String amenities;
    private String developer;
}


