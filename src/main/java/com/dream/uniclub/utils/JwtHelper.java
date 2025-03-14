package com.dream.uniclub.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

    @Value("${jwts.key}")
    private String jwtKey;

    private final int expiredTime = 8 * 60 * 60 * 1000;

    public String generateToken(String data) {
        // Biến key kiểu string đã lưu trữ trc đó thành SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
        Date currentDate = new Date();
        long milliSecondFuture = currentDate.getTime() + expiredTime;
        Date futureDate = new Date(milliSecondFuture);

        String token = Jwts.builder().signWith(secretKey).expiration(futureDate).subject(data).compact();

        return token;
    }

    public String decodeToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtKey));

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
