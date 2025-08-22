package com.nepalibazar.core.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {

    private JwtUtils() {
    }

    private static final MacAlgorithm ALGORITHM= Jwts.SIG.HS256;

    private static final SecretKey SECRET_KEY=ALGORITHM.key().build();

    private static final long EXPIRATION_TIME= 1000*60*60; //1 hour

    public static String generateToken(String emailPhone){
        return Jwts.builder()
                .subject(emailPhone)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SECRET_KEY,ALGORITHM)
                .compact();
    }

    public static String extractEmailPhone(String token){
       return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static boolean validateToken(String token, String emailPhone){
        try{
            String extractedEmailPhone=extractEmailPhone(token);
            return extractedEmailPhone.equals(emailPhone) && !isTokenExpired(token);
        }catch(JwtException e){
            return false;

        }
    }

    public static boolean isTokenExpired(String token){
        try{
            Date expiration= Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration();

            return expiration.before(new Date());

        }catch (JwtException e){
            return true;
        }
    }

    public static String getEmailFromToken(String token){
        try{
            return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getBody()
                    .getSubject();
        }catch (JwtException e){
            throw new RuntimeException("Invalid token"+ e.getMessage());
        }
    }
}