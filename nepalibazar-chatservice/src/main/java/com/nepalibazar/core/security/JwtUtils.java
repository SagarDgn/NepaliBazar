package com.nepalibazar.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtils {

    // FIXED: Use same shared secret as Micronaut
    private static final String SHARED_SECRET = "uhcwuhuoehywuoycue743nfey7e83y7ney37yucuy3y48374yf7y47";
    private static final SecretKey SECRET_KEY = new SecretKeySpec(
            SHARED_SECRET.getBytes(),
            "HmacSHA256"
    );

    // FIXED: Use same algorithm as Micronaut
    private static final MacAlgorithm ALGORITHM = Jwts.SIG.HS256;

    private JwtUtils() {
    }

    // FIXED: Use same new JJWT API as Micronaut
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)  // Same API as Micronaut
                .build()
                .parseSignedClaims(token)  // Same API as Micronaut
                .getPayload();
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