package com.ecommerce.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import com.ecommerce.common.Role;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;  // 24 hours

    // Use a fixed secure key (at least 256-bit for HS256)
    private static final String SECRET_KEY = "a-very-secure-and-long-secret-key-123456789012"; // Must be at least 32 chars
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // Generate JWT token
    public String generateToken(String subject, Role role) {
        System.out.println("Generating token for role: " + role.name());

        return Jwts.builder()
                .setSubject(subject)
                .claim("role", role.name())

                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    // Validate JWT token
    public Claims validateToken(String token) {
        try {
            System.out.println("Validating token: " + token);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("Token validated successfully");
            System.out.println("Claim" + claims);
            return claims;
            

        } catch (MalformedJwtException e) {
            System.out.println("Malformed JWT token: " + e.getMessage());
            throw new IllegalArgumentException("Invalid JWT token format");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token: " + e.getMessage());
            throw new IllegalArgumentException("JWT token expired");
        } catch (io.jsonwebtoken.security.SignatureException e) {  // <== updated here
            System.out.println("JWT signature mismatch: " + e.getMessage());
            throw new IllegalArgumentException("JWT signature mismatch");

        } catch (JwtException e) {
            System.out.println("Invalid JWT signature or other JWT error: " + e.getMessage());
            throw new IllegalArgumentException("Invalid JWT token");
        } catch (Exception e) {
            System.out.println("Error parsing JWT token: " + e.getMessage());
            throw new IllegalArgumentException("Invalid JWT token");
        }
    }
}