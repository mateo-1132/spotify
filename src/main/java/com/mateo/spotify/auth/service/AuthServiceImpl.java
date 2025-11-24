package com.mateo.spotify.auth.service;

import com.mateo.spotify.auth.dto.LoginRequestDTO;
import com.mateo.spotify.auth.dto.TokenResponseDTO;
import com.mateo.spotify.user.dto.CreateUserRequestDTO;
import com.mateo.spotify.user.dto.UserResponseDTO;
import com.mateo.spotify.user.entity.UserEntity;
import com.mateo.spotify.user.mapper.UserMapper;
import com.mateo.spotify.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public TokenResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.username(),
                            loginRequestDTO.password()
                    )
            );
        }catch (AuthenticationException authenticationException){
            throw new BadCredentialsException("Credenciales invalidas");
        }

        UserEntity userEntity = userRepository.findByUsername(loginRequestDTO.username())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        String accessToken = jwtService.generateToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        return new TokenResponseDTO(accessToken,refreshToken);
    }

    @Override
    public TokenResponseDTO refresh(String refreshToken) {
        return null;
    }

    @Override
    public UserResponseDTO signup(CreateUserRequestDTO createUserRequestDTO) {
        if (userRepository.existsByUsername(createUserRequestDTO.username())){
            throw new RuntimeException("User already exists");
        }

        if (userRepository.existsByEmail(createUserRequestDTO.password())){
            throw new RuntimeException("User already exists");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(createUserRequestDTO.username())
                .email(createUserRequestDTO.email())
                .password(passwordEncoder.encode(createUserRequestDTO.password()))
                .build();

        UserEntity userEntitySaved = userRepository.save(userEntity);

        return userMapper.toDTO(userEntitySaved);

    }
}
