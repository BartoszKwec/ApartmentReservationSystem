package pl.kwec.apartmentreservationsystem.registration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterRequest {
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    @NotEmpty
    private String email;
}
