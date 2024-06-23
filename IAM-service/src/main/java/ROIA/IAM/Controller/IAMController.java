package ROIA.IAM.Controller;

import ROIA.IAM.DTO.UserRequest;
import ROIA.IAM.DTO.UserResponse;
import ROIA.IAM.Service.IAMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/iam")
public class IAMController {

    private final IAMService userService;

    @PostMapping("/register")
    public UserResponse userSignUp(@RequestBody UserRequest userRequest) throws Exception {
        return userService.userSignUp(userRequest);
    }

    @PostMapping(value = "/login")
    public UserResponse userSignIn(@RequestBody UserRequest userRequest) throws Exception {
        return userService.userSignIn(userRequest);
    }

}
