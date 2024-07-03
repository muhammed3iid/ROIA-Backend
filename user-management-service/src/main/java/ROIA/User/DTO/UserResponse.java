package ROIA.User.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String mobileNumber;
}
