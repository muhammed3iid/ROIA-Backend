package ROIA.IAM.DTO;

import lombok.Data;

@Data
public class IAMRequest {
    private String email;
    private String username;
    private String password;
}
