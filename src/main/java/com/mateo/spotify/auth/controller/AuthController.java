package com.mateo.spotify.auth.controller;

import com.mateo.spotify.auth.dto.LoginRequestDTO;
import com.mateo.spotify.auth.dto.TokenResponseDTO;
import com.mateo.spotify.auth.service.AuthService;
import com.mateo.spotify.user.dto.CreateUserRequestDTO;
import com.mateo.spotify.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<@NonNull TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<@NonNull UserResponseDTO> signup(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(createUserRequestDTO));
    }

    @GetMapping("/refresh/{refreshToken}")
    public ResponseEntity<@NonNull TokenResponseDTO> refresh(@PathVariable String refreshToken){
        return ResponseEntity.status(HttpStatus.OK).body(authService.refresh(refreshToken));
    }

}
