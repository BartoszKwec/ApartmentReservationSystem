package pl.kwec.apartmentreservationsystem.authentication.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.authentication.login.model.LoginRequest;
import pl.kwec.apartmentreservationsystem.authentication.login.model.LoginResponse;
import pl.kwec.apartmentreservationsystem.token.JwtUtils;
import pl.kwec.apartmentreservationsystem.token.TokenService;
import pl.kwec.apartmentreservationsystem.user.UserService;
import pl.kwec.apartmentreservationsystem.user.model.User;


@Service
@RequiredArgsConstructor
public class LoginService {

    private static final String LOGIN_ERROR_MESSAGE = "Login error. Check if your login and password are correct";
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public ResponseEntity<?> login(final LoginRequest request) {
        try {
            final String login = request.getLogin();
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login,
                            request.getPassword()));
            final String jwtToken = JwtUtils.generateToken(authentication);
            final User user = userService.findByLogin(login);

            tokenService.saveToken(user, jwtToken);

            final LoginResponse response = LoginResponse.builder()
                    .accessToken(jwtToken)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (final AuthenticationException exception) {
            final ErrorResponse response = ErrorResponse.builder()
                    .message(LOGIN_ERROR_MESSAGE)
                    .statusCode(HttpStatus.UNAUTHORIZED)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}