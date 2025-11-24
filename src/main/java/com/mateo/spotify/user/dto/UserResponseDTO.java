package com.mateo.spotify.user.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        String profilePictureUrl
) {
}
