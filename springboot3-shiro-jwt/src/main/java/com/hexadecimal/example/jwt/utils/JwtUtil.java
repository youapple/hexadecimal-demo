package com.hexadecimal.example.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    private final byte[] secret = "zxcvbnmfdasaererafafafafafafakjlkjalkfafadffdafadfafafaaafadfadfaf1234567890".getBytes(StandardCharsets.UTF_8);
    private static final long expire = 60 * 24 * 7;
    public static final String HEADER = "Authorization";

    /**
     * 生成jwt token
     */
    public String generateToken(Integer userId) {
        SecretKey signingKey = Keys.hmacShaKeyFor(secret);
        //过期时间
        LocalDateTime tokenExpirationTime = LocalDateTime.now().plusMinutes(expire);
        return Jwts.builder()
                .signWith(signingKey, Jwts.SIG.HS512)
                .header().add("typ", "JWT").and()
                .issuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .subject(String.valueOf(userId))
                .expiration(Timestamp.valueOf(tokenExpirationTime))
                //.claims(Map.of("key", "value"))
                .compact();
    }

    public Claims getClaimByToken(String token) {
        SecretKey signingKey = Keys.hmacShaKeyFor(secret);
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 检查token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(1);
        System.out.println("token = " + token);

        Claims claims = jwtUtil.getClaimByToken(token);
        System.out.println("claims = " + claims);
    }
}
