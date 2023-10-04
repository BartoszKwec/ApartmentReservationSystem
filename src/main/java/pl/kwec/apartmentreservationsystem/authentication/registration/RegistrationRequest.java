package pl.kwec.apartmentreservationsystem.authentication.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kwec.apartmentreservationsystem.user.Role;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}
