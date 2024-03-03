package com.nocountrys13.ecoapp.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDtoRequest(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @Email @NotBlank String email,
        @NotBlank String password) {
}
