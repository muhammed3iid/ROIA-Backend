package ROIA.User.Service;

import ROIA.User.DTO.UserRequest;
import ROIA.User.DTO.UserResponse;
import ROIA.User.Model.User;
import ROIA.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        try {
            User user = User.builder()
                    .id(userRequest.getId())
                    .email(userRequest.getEmail())
                    .username(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .build();
            userRepository.save(user);
            return mapModelToResponse(user);
        } catch (Exception e) {
            throw new RuntimeException("Error during creating user: " + e.getMessage());
        }
    }

    public UserResponse editUser(UserRequest userRequest) {
        try {
            User user = userRepository.findUserById(userRequest.getId());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setMobileNumber(userRequest.getMobileNumber());
            userRepository.save(user);
            return mapModelToResponse(user);
        } catch (Exception e) {
            throw new RuntimeException("Error during filling information: " + e.getMessage());
        }
    }

    public UserResponse mapModelToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .build();
    }

}
