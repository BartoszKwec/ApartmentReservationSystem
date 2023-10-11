package pl.kwec.apartmentreservationsystem.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.kwec.apartmentreservationsystem.token.JwtTokenValidator;
import pl.kwec.apartmentreservationsystem.token.JwtUtils;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenValidator tokenValidator;

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                    @NonNull final HttpServletResponse response,
                                    @NonNull final FilterChain filterChain) throws ServletException, IOException {
        final Optional<String> jwtToken = JwtUtils.obtainJwtTokenFromRequest(request);

        jwtToken.ifPresent(token -> authenticate(request, token));
        filterChain.doFilter(request, response);
    }

    private void authenticate(final HttpServletRequest request, final String jwtToken) {
        final String username = JwtUtils.extractUsername(jwtToken);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (tokenValidator.validate(jwtToken, userDetails)) {
            updateSecurityContext(request, userDetails);
        }
    }

    private void updateSecurityContext(final HttpServletRequest request, final UserDetails userDetails) {
        final UsernamePasswordAuthenticationToken authenticationToken = obtainAuthenticationToken(request, userDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UsernamePasswordAuthenticationToken obtainAuthenticationToken(final HttpServletRequest request,
                                                                          final UserDetails userDetails) {
        final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return authToken;
    }
}