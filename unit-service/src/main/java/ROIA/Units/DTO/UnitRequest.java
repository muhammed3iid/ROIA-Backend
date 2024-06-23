package ROIA.Units.DTO;

import lombok.Data;

@Data
public class UnitRequest {
    private String category;
    private String type;
    private int n_bedrooms;
    private int n_bathrooms;
}
