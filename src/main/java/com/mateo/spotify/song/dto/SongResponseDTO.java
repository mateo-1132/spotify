package com.mateo.spotify.song.dto;

import com.mateo.spotify.user.dto.UserResponseDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record SongResponseDTO(
        UUID id,
        String title,
        double duration,
        LocalDate date,
        String imageUrl,
        String songUrl,
        UserResponseDTO author,
        GenreResponseDTO genre
) {
}
