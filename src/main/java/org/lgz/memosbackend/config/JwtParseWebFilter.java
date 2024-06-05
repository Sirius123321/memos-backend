package org.lgz.memosbackend.config;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Parse jwt token from HTTP Authorization Header to the next in the chain.
 * <p>
 * e.g. Authorization: Bearer jwtTokenString -> jwtTokenString
 *
 * @author Lin Guangzhe
 */
@Component
public class JwtParseWebFilter implements WebFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    @Nonnull
    public Mono<Void> filter(@Nonnull ServerWebExchange exchange,@Nonnull WebFilterChain chain) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith(BEARER_PREFIX))
                .map(authHeader -> authHeader.substring(BEARER_PREFIX.length()))
                .flatMap(token -> chain.filter(exchange));
    }
}
