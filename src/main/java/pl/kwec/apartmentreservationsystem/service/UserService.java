package pl.kwec.apartmentreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.model.User;
import pl.kwec.apartmentreservationsystem.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final String EMAIL_ALREADY_TAKEN = "User with %s is already used.";

    @Autowired
    public UserService(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(final String email, final String password, final String name,
                             final String surname) {

        userRepo.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException(String.format(EMAIL_ALREADY_TAKEN, email));
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
