package org.lgz.memosbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtAuthenticationManager jwtAuthenticationManager;
    private final JwtParseWebFilter jwtParseWebFilter;

    public SecurityConfig(JwtAuthenticationManager jwtAuthenticationManager, JwtParseWebFilter jwtParseWebFilter) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.jwtParseWebFilter = jwtParseWebFilter;
    }
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
//                .addFilterAt(jwtParseWebFilter,SecurityWebFiltersOrder.AUTHENTICATION)  // parse jwt token from http header
//                .authenticationManager(jwtAuthenticationManager)
                .authorizeExchange((authorize) -> authorize
//                                .anyExchange().permitAll()  // test, permit all request
                        .anyExchange().authenticated()  // all exchange must have credentials, exclude specific endpoint before this line. e.g. pathMatchers("/endpoint").permitAll().
                )
                .httpBasic(withDefaults())  // HTTP Basic Authentication(RFC7617),string in http header: Authorization: Basic base64(username:password)
                .exceptionHandling((exceptionHandling) ->exceptionHandling
                        .authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/token")));
        return http.build();
    }


@Bean
    PasswordEncoder passwordEncoder() {
        // use bcrypt password processor, the password will be hashed in database
        // e.g. 123456 -> $2a$10$p9.VR3FHfKHk4/qFHkPbNOIpuGdW1TLRB1yuPnqJcM.7CJkDq0tla
        return new BCryptPasswordEncoder();
    }
}