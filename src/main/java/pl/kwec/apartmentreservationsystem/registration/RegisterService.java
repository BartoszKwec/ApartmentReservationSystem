package pl.kwec.apartmentreservationsystem.registration;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.user.UserModel;
import pl.kwec.apartmentreservationsystem.user.UserRepo;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private static final String USER_ALREADY_EXIST = "User already exist";
    private static final String USER_CRATED_SUCCESSFUL = "Register successful";
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public ResponseEntity<String> register(RegisterRequest request) {

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return new ResponseEntity<>(USER_CRATED_SUCCESSFUL, HttpStatus.BAD_REQUEST);
        }
        //walidacja -osobna klasa
        userRepo.findByEmail(request.getEmail()).ifPresent(userModel -> {
            throw new RuntimeException(USER_ALREADY_EXIST);
        });

        UserModel userModel = obtainUserFromRequest(request);
        userRepo.save(userModel);
        return new ResponseEntity<>(USER_CRATED_SUCCESSFUL, HttpStatus.CREATED);
    }

    private UserModel obtainUserFromRequest(RegisterRequest request) {
        return UserModel.builder()
                .name(request.getFirstname())
                .surname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();
    }
}
