package com.mateo.spotify.auth.service;

import com.mateo.spotify.user.entity.UserEntity;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(UserEntity userEntity);
    String generateRefreshToken(UserEntity userEntity);
    boolean isTokenValid(String token);
    Claims extractClaims(String token);
}
