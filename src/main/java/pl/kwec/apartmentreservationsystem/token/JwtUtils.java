package pl.kwec.apartmentreservationsystem.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

public class JwtUtils {

    private static final String BEARER = "Bearer ";
    private static final int BEGIN_INDEX_OF_JWT_TOKEN = 7;

    private static final String test = "eyJhbGciOiJIUzUxMiJ9eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTY4NDU3NTQ5MywiaWF0IjoxNjg0NTc1NDkzfQ5exDuHqHX1Dh8UW1c20IfGSymKzxozvYvLl0WPrvaqLyy67kSYtz6F9lXpuZV0KWSZT00DP8dE2VHL0DUQnppg";

    @Value("${application.security.jwt.secret-key}")
    private  String secretKey;

    @Value("${application.security.jwt.expiration}")
    private static long jwtExpiration;

    public static String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(test);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Optional<String> obtainJwtTokenFromRequest(final @NonNull HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        return Optional.ofNullable(authHeader)
                .filter(header -> StringUtils.hasText(header) && header.startsWith(BEARER))
                .map(header -> header.substring(BEGIN_INDEX_OF_JWT_TOKEN));
    }

    public static String generateToken(final Authentication user) {
        final String username = user.getName();
        final Date currentDate = new Date();
        final Date expireDate = new Date(currentDate.getTime() + 60000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(JwtUtils.getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }
}
