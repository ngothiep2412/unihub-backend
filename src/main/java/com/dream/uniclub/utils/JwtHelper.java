package com.dream.uniclub.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtHelper {

    @Value("${jwts.key}")
    private String jwtKey;

    public String generateToken(String data) {
        // Biến key kiểu string đã lưu trữ trc đó thành SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
        String token = Jwts.builder().signWith(secretKey).subject(data).compact();

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
