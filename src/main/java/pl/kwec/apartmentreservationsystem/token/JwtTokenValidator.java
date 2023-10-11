package pl.kwec.apartmentreservationsystem.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenValidator {

    public boolean validate(final String token, final UserDetails userDetails) {
        final String username = JwtUtils.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(final String token) {
        return JwtUtils.extractExpiration(token).before(new Date());
    }
}
