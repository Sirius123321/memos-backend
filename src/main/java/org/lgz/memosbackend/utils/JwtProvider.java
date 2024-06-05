package org.lgz.memosbackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.neo4j.cypherdsl.core.Use;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;  // 864 000 000 ms = 10 days

    public String generateToken(User user) {
        String username = user.getUsername();
        List<String> role = new ArrayList<>();
        user.getAuthorities().forEach((grantedAuthority ->role.add(grantedAuthority.toString())));  // java 8 lambda expression
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).addClaims(Map.of("roles",role)).signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }
    public boolean validateToken(String token) {
        try{
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody();
            return claims != null;
        }catch (Exception ignore){
            return false;
        }
    }
    public UserDetails getUserFromToken(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody();
        String username = claims.getSubject();  // parse from jwt token
        List<String> role = new ArrayList<>();  // parse from jwt token
        claims.get("roles",List.class).forEach((grantedAuthority ->role.add(grantedAuthority.toString())));
        User.UserBuilder userBuilder = User.builder().username(username);
        role.forEach(grantedAuthority -> userBuilder.authorities(new SimpleGrantedAuthority(grantedAuthority)));  // java 8 lambda expression, role string convert to specific class
        return userBuilder.build();
    }
}
