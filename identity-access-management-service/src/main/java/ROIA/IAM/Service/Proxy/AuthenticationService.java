package ROIA.IAM.Service.Proxy;

import ROIA.IAM.Model.User;
import ROIA.IAM.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthentication {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User proxySignUp(User user) {
        return userRepository.save(user);
    }

    @Override
    public User proxySignIn(User user) {
        return user;
    }
}
