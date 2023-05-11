package pl.kwec.apartmentreservationsystem.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "The email cannot be empty")
    @Email(message = "Email is invalid")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "The password cannot be empty")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "The name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "The surname cannot be empty")
    private String surname;

}
