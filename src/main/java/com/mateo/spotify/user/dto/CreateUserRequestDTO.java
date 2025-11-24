package com.mateo.spotify.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequestDTO(
        @NotBlank(message = "El email no puede estar vacio")
        @Email(message = "El email no cumple el formato valido")
        String email,
        @NotBlank(message = "El nombre de usuario no puede estar vacio")
        String username,
        @NotBlank(message = "La contraseña no puede estar vacia")
        @Length(min = 8, message = "La contraseña debe de tener al menos 8 caracteres")
        String password
) {
}
