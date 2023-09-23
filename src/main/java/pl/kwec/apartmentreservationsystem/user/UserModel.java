package pl.kwec.apartmentreservationsystem.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;

@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class UserModel implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
