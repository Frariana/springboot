package com.springboot.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtBuilder.BuilderHeader;
import io.jsonwebtoken.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.security.Key;
import javax.crypto.SecretKey;

public class Jwt {

    public static final int EXPIRATION_IN_MINUTES = 30;
    public static final String SECRET_KEY = "MI CLAVE ES SECRETA";

    public String getJwt(String subject) {
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("name", "frariana c");
//        Date issuedAt = new Date(System.currentTimeMillis());
//        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_IN_MINUTES * 60 * 1000);
//        String jwt = Jwts.builder()
//                .header()
//                .type("JWT")
//                .and()
//                .subject("fcastro")
//                .expiration(expiration)
//                .issuedAt(issuedAt)
//                .claims(extraClaims)
//                .signWith(, Jwts.SIG.HS256)
//                .compact();
        SecretKey key = Jwts.SIG.HS256.key().build();
        String jws = Jwts.builder().subject(subject).signWith(key).compact();
        return jws;
    }

    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
