package ROIA.User.Repository;

import ROIA.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String id);
}