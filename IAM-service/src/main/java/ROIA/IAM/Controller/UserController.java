package ROIA.IAM.Controller;

import ROIA.IAM.DTO.UserRequest;
import ROIA.IAM.DTO.UserResponse;
import ROIA.IAM.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse userSignUp(@RequestBody UserRequest userRequest) throws Exception {
        return userService.userSignUp(userRequest);
    }

    @GetMapping("/login")
    public UserResponse userSignIn(@RequestBody UserRequest userRequest) throws Exception {
        return userService.userSignIn(userRequest);
    }

}
