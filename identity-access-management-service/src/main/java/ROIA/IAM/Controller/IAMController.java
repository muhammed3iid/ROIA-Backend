package ROIA.IAM.Controller;

import ROIA.IAM.DTO.IAMResponse;
import ROIA.IAM.Service.IAMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/iam")
public class IAMController {

    private final IAMService iamService;

    @GetMapping("/check-username")
    public boolean isUsernameValid(@RequestParam String username) {
        return iamService.isUsernameValid(username);
    }

    @GetMapping("/check-email")
    public boolean isEmailValid(@RequestParam String email) {
        return iamService.isEmailValid(email);
    }

    @PostMapping("/register")
    public IAMResponse userSignUp(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        return iamService.userSignUp(email, username, password);
    }

    @GetMapping(value = "/login")
    public IAMResponse userSignIn(@RequestParam String key, @RequestParam String password) throws Exception {
        return iamService.userSignIn(key, password);
    }

}
