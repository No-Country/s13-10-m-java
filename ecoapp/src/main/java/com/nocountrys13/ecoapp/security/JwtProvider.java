package com.nocountrys13.ecoapp.security;

import com.nocountrys13.ecoapp.dtos.Jwt;
import com.nocountrys13.ecoapp.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private SecretKey key;

    @PostConstruct
    private void init() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.SECRET_KEY));
    }

    private Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean extractExpiration(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public Jwt generateToken(Usuario user) {
        var token = Jwts.builder()
                .claim("ROLE", List.of(Role.USER.getAuthority()))
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return new Jwt(token);
    }

}


