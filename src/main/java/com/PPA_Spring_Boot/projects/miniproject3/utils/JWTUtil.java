package com.PPA_Spring_Boot.projects.miniproject3.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET = "bhaski";

    private static final long AUTH_TOKEN_EXPIRY = 864_000_000;
    private static final long REFRESH_TOKEN_EXPIRY = 1200_000_000;

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + AUTH_TOKEN_EXPIRY))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRY))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static boolean validateToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token);

        String subject = claimsJws.getBody().getSubject();

        return !subject.isEmpty() &&
                !isTokenExpired(token);
    }

    public static boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().before(new Date());
    }
}
