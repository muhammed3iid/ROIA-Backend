package ROIA.Unit.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnitResponse {
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
