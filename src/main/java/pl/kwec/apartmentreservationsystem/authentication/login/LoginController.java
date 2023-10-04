package pl.kwec.apartmentreservationsystem.authentication.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kwec.apartmentreservationsystem.authentication.login.model.LoginRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody final LoginRequest request) {
        return loginService.login(request);
    }
}