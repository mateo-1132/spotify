package com.mateo.spotify.auth.dto;

public record TokenResponseDTO(
        String AccessToken,
        String RefreshToken
) {
}
