package pl.kwec.apartmentreservationsystem.authentication.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.user.UserRepository;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RegistrationValidator {
    private static final String LOGIN_IS_TAKEN = "Login is taken!";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String INVALID_EMAIL_ADDRESS = "Invalid email address";

    private final UserRepository userRepository;

    public void validate(RegistrationRequest request) {
        validateLogin(request.getLogin());
        validateEmailAddress(request.getEmail());
        //walidacja roli i donyślne jej ustawienie
    }

    private void validateLogin(final String login) {
        if (userRepository.existsByLogin(login)) {
            throw new RegistrationException(LOGIN_IS_TAKEN);
        }
    }

    private void validateEmailAddress(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        if (!pattern.matcher(email).matches()) {
            throw new RegistrationException(INVALID_EMAIL_ADDRESS);
        }
    }
}