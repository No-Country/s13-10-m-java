package com.nocountrys13.ecoapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(@NotBlank String nombre,
                         @NotBlank String apellido,
                         @Email @NotBlank String email,
                         @NotBlank String password) {
}
