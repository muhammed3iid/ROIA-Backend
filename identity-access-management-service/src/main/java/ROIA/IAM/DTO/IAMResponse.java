package ROIA.IAM.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IAMResponse {
    private String id;
    private String email;
    private String username;
}
