package pl.kwec.apartmentreservationsystem.token;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.user.model.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String LOGGED_MESSAGE = "Logged";

    private final TokenRepository repository;

    public Optional<Token> findByJwtToken(final String jwtToken) {
        return repository.findByToken(jwtToken);
    }

    public void saveToken(final User user, final String jwtToken) {
        final Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        repository.save(token);
    }

    public ResponseEntity<String> invalidateToken(final Token token) {
        token.setExpired(true);
        token.setRevoked(true);
        repository.save(token);
        SecurityContextHolder.clearContext();

        return new ResponseEntity<>(LOGGED_MESSAGE, HttpStatus.OK);
    }
}
