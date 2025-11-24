package com.mateo.spotify.auth.service;

import com.mateo.spotify.auth.dto.LoginRequestDTO;
import com.mateo.spotify.auth.dto.TokenResponseDTO;
import com.mateo.spotify.user.dto.CreateUserRequestDTO;
import com.mateo.spotify.user.dto.UserResponseDTO;

public interface AuthService {
    TokenResponseDTO login(LoginRequestDTO loginRequestDTO);
    TokenResponseDTO refresh(String refreshToken);
    UserResponseDTO signup(CreateUserRequestDTO createUserRequestDTO);

}
