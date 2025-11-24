package com.mateo.spotify.auth.service;

import com.mateo.spotify.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${spring.security.jwt.secret}")
    private String secretKey;
    @Value("${spring.security.jwt.expiration}")
    private long expiration;
    @Value("${spring.security.jwt.refresh.expiration}")
    private long refreshExpiration;


    @Override
    public String generateToken(UserEntity userEntity) {
        return buildToken(userEntity,expiration);
    }

    @Override
    public String generateRefreshToken(UserEntity userEntity) {
        return buildToken(userEntity,refreshExpiration);
    }

    @Override
    public boolean isTokenValid(String token) {
        try{
            Jwts.parser()
                    .verifyWith(getSignedKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (SignatureException _){
            return false;
        }catch(JwtException _){
            return false;
        }
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignedKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String buildToken(UserEntity user, long expiration){
        Map<String,String> claims = new HashMap<>();
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .id(user.getId().toString())
                .subject(user.getUsername())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignedKey())
                .compact();

    }
}
