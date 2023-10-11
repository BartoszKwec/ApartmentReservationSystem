package pl.kwec.apartmentreservationsystem.authentication.logout;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/logout")
public class LogoutController {
    private final LogoutService logoutService;

    @PostMapping
    public ResponseEntity<?> logout(HttpServletRequest request) {
        return logoutService.logout(request);
    }
}
