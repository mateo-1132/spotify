package com.mateo.spotify.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "El nombre de usuario no puede estar vacio")
        String username,
        @NotBlank(message = "La contraseña no puede estar vacia")
        String password
) {
}
