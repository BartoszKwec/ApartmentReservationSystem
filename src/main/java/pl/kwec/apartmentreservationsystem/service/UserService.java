package pl.kwec.apartmentreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.model.User;
import pl.kwec.apartmentreservationsystem.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;
    final String EMAIL_ALREADY_TAKEN = "This email is already used.";

    @Autowired
    public UserService(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(final String email, final String password, final String name,
                             final String surname) throws Exception {

        userRepo.findByEmail(email).ifPresent(user -> {
            throw new RuntimeException(EMAIL_ALREADY_TAKEN);
        });

        final User user = User.builder()
                .name(name)
                .email(email)
                .surname(surname)
                .password(password)
                .build();
        userRepo.save(user);
    }
}
