package ROIA.User.DTO;

import lombok.Data;

@Data
public class UserRequest {
    private String id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
}
