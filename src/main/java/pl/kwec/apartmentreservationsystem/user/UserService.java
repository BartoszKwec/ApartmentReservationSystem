package pl.kwec.apartmentreservationsystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        userRepo.findByEmail(email).ifPresent(userModel -> {
            throw new IllegalArgumentException(String.format(EMAIL_ALREADY_TAKEN, email));
        });

        final UserModel userModel = UserModel.builder()
                .name(name)
                .email(email)
                .surname(surname)
                .password(password)
                .build();
        userRepo.save(userModel);
    }
}
