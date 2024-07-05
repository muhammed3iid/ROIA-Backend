package com.ROIA.shared_unit.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "shared_units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharedUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unitName;
    private String description;
    private String location;
    private double totalValue;
    private int totalShares;
    private int availableShares;
    private double pricePerShare;

    @OneToMany(mappedBy = "sharedUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Share> shares;
}
