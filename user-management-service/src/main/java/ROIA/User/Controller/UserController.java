package ROIA.User.Controller;

import ROIA.User.DTO.UserRequest;
import ROIA.User.DTO.UserResponse;
import ROIA.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create-user")
    public UserResponse createUserProfile(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/edit-user")
    public UserResponse editUserProfile(@RequestBody UserRequest userRequest) {
        return userService.editUser(userRequest);
    }


}
