package ROIA.IAM.Service;

import ROIA.IAM.DTO.IAMResponse;
import ROIA.IAM.DTO.UserRequest;
import ROIA.IAM.DTO.UserResponse;
import ROIA.IAM.Model.Credentials;
import ROIA.IAM.Repository.IAMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
@RequiredArgsConstructor
public class IAMService {

    private final IAMRepository iamRepository;
    private final WebClient webClient;

    public boolean isUsernameValid(String username) {
        try {
            return !iamRepository.existsByUsernameIgnoreCase(username);
        } catch (Exception e) {
            throw new RuntimeException("Error checking if username is valid: " + e.getMessage());
        }
    }

    public boolean isEmailValid(String email) {
        try {
            return !iamRepository.existsByEmailIgnoreCase(email);
        } catch (Exception e) {
            throw new RuntimeException("Error checking if email is valid: " + e.getMessage());
        }
    }

    @Transactional
    public IAMResponse userSignUp(String email, String username, String password) {
        try {
            Credentials credentials = Credentials.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .build();
            iamRepository.save(credentials);
            UserRequest userRequest = UserRequest.builder()
                    .id(credentials.getId())
                    .email(credentials.getEmail())
                    .username(credentials.getUsername())
                    .password(credentials.getPassword())
                    .build();

            UserResponse userResponse = webClient.post()
                    .uri("http://localhost:8080/api/user/create-user")
                    .bodyValue(userRequest)
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .block();

            if (userResponse == null) {
                throw new RuntimeException("Error during user sign-up: user response is null");
            }
            return mapModelToResponse(credentials);
        } catch (Exception e) {
            throw new RuntimeException("Error during user sign-up: " + e.getMessage());
        }
    }

    public IAMResponse userSignIn(String key, String password) throws Exception {
        try {
            Credentials credentials = iamRepository.getCredentialsByEmailIgnoreCase(key);
            if (credentials == null) {
                credentials = iamRepository.getCredentialsByUsernameIgnoreCase(key);
                if (credentials == null) {
                    throw new Exception("Username or email does not exist.");
                }
            }
            if (credentials.getPassword().equals(password)) {
                return mapModelToResponse(credentials);
            } else {
                throw new Exception("Password is not correct.");
            }
        } catch (Exception e) {
            throw new Exception("Authentication failed: " + e.getMessage());
        }
    }

    public IAMResponse mapModelToResponse(Credentials credentials) {
        return IAMResponse.builder()
                .id(credentials.getId())
                .username(credentials.getUsername())
                .email(credentials.getEmail())
                .build();
    }

}
