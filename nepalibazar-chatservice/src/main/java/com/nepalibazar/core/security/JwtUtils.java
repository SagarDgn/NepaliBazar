package com.nepalibazar.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {

    // Use same approach as Micronaut
    private static final MacAlgorithm ALGORITHM = Jwts.SIG.HS256;
    private static final SecretKey SECRET_KEY = ALGORITHM.key().build();
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private JwtUtils() {
        // private constructor to prevent instantiation
    }

    // Parse token and return claims
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract email/username from token
    public static String extractEmail(String token) {
        return parseToken(token).getSubject();
    }

    // Extract role from token
    public static String extractRole(String token) {
        return parseToken(token).get("role", String.class);
    }

    // Check if token is expired
    public static boolean isTokenExpired(String token) {
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(new Date());
    }

    // Validate token against a user email
    public static boolean validateToken(String token, String email) {
        try {
            String extractedEmail = extractEmail(token);
            return extractedEmail.equals(email) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

}
