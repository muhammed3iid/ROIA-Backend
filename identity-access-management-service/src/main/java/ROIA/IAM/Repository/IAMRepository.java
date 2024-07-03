package ROIA.IAM.Repository;

import ROIA.IAM.Model.Credentials;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAMRepository extends MongoRepository<Credentials, String> {
    Boolean existsByUsernameIgnoreCase(String username);
    Boolean existsByEmailIgnoreCase(String email);
    Credentials getCredentialsByEmailIgnoreCase(String email);
    Credentials getCredentialsByUsernameIgnoreCase(String username);
}
