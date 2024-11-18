package com.forty.cg.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecretKeyBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
public class JWTUtils {
    private static final String SUBJECT = "forty";

    // 单位:秒 60 * 60 * 24 = 1 day
    private static final int EXPIRE_TIME = 60 * 60 * 24;


    public static String encrypt(Map<String, Object> map, String secretKey){
        return encrypt(map, secretKey, EXPIRE_TIME);
    }

    /**
     *
     * @param map: user defined map data
     * @param secretKey: application secret key
     * @param expire_time: it will not expire if expire time equal to 0.
     * @return token string
     */
    public static String encrypt(Map<String, Object> map, String secretKey, int expire_time) {
        // Must over 256 bits if you using HMAC
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        Date expireDate = Date.from(Instant.now().plusSeconds(EXPIRE_TIME));
        JwtBuilder claims = Jwts.builder().signWith(key).subject(SUBJECT).claims(map);
        if (expire_time > 0) claims.expiration(expireDate);
        return claims.compact();
    }

    public static Map<String, Object> decrypt(String token, String secretKey) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        try {
            Jws<Claims> jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            Claims payload = jws.getPayload();
            Map<String, Object> map = new HashMap<>();
            payload.forEach((_key, _value) -> map.put(_key, _value.toString()));
            return map;
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    public static Boolean isTokenExpired(String token, String secretKey) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        Jwt<?, ?> parse = Jwts.parser().verifyWith(key).build().parse(token);
        return false;
    }
}
