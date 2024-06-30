package ROIA.IAM.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean enabled;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;

}


