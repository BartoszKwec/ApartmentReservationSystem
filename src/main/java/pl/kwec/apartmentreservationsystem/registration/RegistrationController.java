package pl.kwec.apartmentreservationsystem.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegisterService service;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody final RegisterRequest request) {
        return service.register(request);
    }
}
