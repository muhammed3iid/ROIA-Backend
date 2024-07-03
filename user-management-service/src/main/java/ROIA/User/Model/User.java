package ROIA.User.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;


}