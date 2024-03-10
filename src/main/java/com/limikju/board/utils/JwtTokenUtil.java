package com.limikju.board.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private static Long expiredMs = 1000 * 60 * 60l;

    private final SecretKey secretKey;

    public JwtTokenUtil(@Value("${jwt.token.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public static String createToken(String userName) {
        String token = Jwts.builder()
                .subject("userName")
                .issuedAt(new Date(System.currentTimeMillis())) //생성일 설정
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) //만료일 설정
                .signWith(Jwts.SIG.HS512.key().build())
                .compact();
        return token;
    }
}
