package pl.kwec.apartmentreservationsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.kwec.apartmentreservationsystem.user.UserService;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfiguration {

    private final UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return userService::findByLogin;
    }
}