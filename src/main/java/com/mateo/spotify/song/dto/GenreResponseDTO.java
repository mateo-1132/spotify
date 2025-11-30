package com.mateo.spotify.song.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record GenreResponseDTO(
        UUID id,
        String name
) {
}
