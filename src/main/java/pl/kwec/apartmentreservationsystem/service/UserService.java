package pl.kwec.apartmentreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.model.User;
import pl.kwec.apartmentreservationsystem.repository.UserRepo;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(String email, String password, String name, String surname) throws Exception {

        if (userRepo.findByEmail(email).isPresent()) {
            throw new Exception("This email is already used.");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        userRepo.save(user);
    }


}
