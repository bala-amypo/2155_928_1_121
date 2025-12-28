
package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final long jwtExpiration;

    // FIX: Added default values after the colon (:) so app starts even if properties are missing
    public JwtTokenProvider(
            @Value("${app.jwt.secret:this_is_a_default_secret_key_for_fallback_purposes_must_be_long_enough}") String secret, 
            @Value("${app.jwt.expiration-milliseconds:3600000}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtExpiration = expiration;
    }

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public Long getUserIdFromToken(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    public String getRoleFromToken(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}