package ROIA.IAM.Service;

import ROIA.IAM.DTO.UserRequest;
import ROIA.IAM.DTO.UserResponse;
import ROIA.IAM.Model.User;
import ROIA.IAM.Service.Proxy.IAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final IAuthentication authenticationProxy;

    @Autowired
    public UserService(IAuthentication authenticationProxy) {
        this.authenticationProxy = authenticationProxy;
    }

    public UserResponse userSignUp(UserRequest userRequest) throws Exception {
        User guest = User.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .mobileNumber(userRequest.getMobileNumber())
                .enabled(true)
                .build();
        User user = authenticationProxy.proxySignUp(guest);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .build();
    }

    public UserResponse userSignIn(UserRequest userRequest) throws Exception {
        User guest = User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();
        User user = authenticationProxy.proxySignIn(guest);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .build();
    }

}
