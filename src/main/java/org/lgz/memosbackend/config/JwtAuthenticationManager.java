package org.lgz.memosbackend.config;

import org.lgz.memosbackend.utils.JwtProvider;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Implements Custom Authorization Manager, validate jwt token and fill user info.
 * @author Lin Guangzhe
 */
@Component  // register to spring bean factory
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtProvider jwtProvider;

    public JwtAuthenticationManager(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();  // rewrite from Jwt, default for password or null
        try {
            UserDetails userDetails = jwtProvider.getUserFromToken(token);
            return Mono.just(new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities()));  // complete user info to next layer
        } catch (Exception e) {
            return null;  // user jwt token validate failed
        }
    }
}
