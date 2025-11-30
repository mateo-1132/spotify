package com.mateo.spotify.song.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateSongRequestDTO(
        @NotBlank(message = "El titulo de la cancion es un campo requerido")
        String title,
        @NotNull(message = "El genero es un campo requerido")
        UUID genreId

) {
}
