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
    public static final String SECRET_KEY = "esta es mi super clave super secreta y super indecifrable 0987654321";

    public String getJwt(String subject) {

        Map<String, Object> extraClaims = buildExtraClaims();

        String jwt = buildJwt(extraClaims, subject);

        return jwt;
    }

    private String buildJwt(Map<String, Object> extraClaims, String subject){
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_IN_MINUTES * 60 * 1000);
        String jwt = Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(subject)
                .expiration(expiration)
                .issuedAt(issuedAt)
                .claims(extraClaims)
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
        return jwt;
    }

    public boolean verificarJwt(String jwt){
        try{
            Claims payload = verifyJws(jwt);
            return true;
        }catch(JwtException e){
            return false;
        }
    }

    private static Claims verifyJws(String jwt){
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(jwt).getPayload();
    }

    private static Map<String, Object> buildExtraClaims(){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", "frariana c");
        return extraClaims;
    }

    private static SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
