package com.mateo.spotify.user.mapper;

import com.mateo.spotify.user.dto.UserResponseDTO;
import com.mateo.spotify.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDTO(UserEntity userEntity){
        return UserResponseDTO.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .username(userEntity.getUsername())
                .profilePictureUrl(userEntity.getProfilePictureUrl())
                .build();
    }
}
