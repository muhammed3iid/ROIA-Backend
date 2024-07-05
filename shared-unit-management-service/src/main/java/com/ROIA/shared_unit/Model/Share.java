package com.ROIA.shared_unit.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shared_units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double shareValue;
    private double percentage;


    private Long userId;

    @ManyToOne
    @JoinColumn(name = "shared_unit_id")
    private SharedUnit sharedUnit;



}
