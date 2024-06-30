package ROIA.IAM.Service.Proxy;

import ROIA.IAM.Model.User;
import ROIA.IAM.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthenticationProxy implements IAuthentication {

    private final UserRepository userRepository;
    private final IAuthentication authenticationService;

    @Autowired
    public AuthenticationProxy(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public User proxySignUp(User user) throws Exception {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail()) || u.getUsername().equals(user.getUsername())) {
                throw new Exception("Email or username already exist.");
            }
        }
        return authenticationService.proxySignUp(user);
    }

    @Override
    public User proxySignIn(User user) throws Exception {
        List<User> users = userRepository.findAll();
        for(User u: users){
            if(u.getUsername().equals(user.getUsername())){
                if(u.getPassword().equals(user.getPassword())){
                    return authenticationService.proxySignIn(u);
                }
                throw new Exception("Password is not correct.");
            }
        }
        throw new Exception("No username found.");
    }

}
