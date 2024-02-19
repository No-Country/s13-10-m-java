package com.nocountrys13.ecoapp.dtos.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDtoResponse(@NotBlank String nombre,
                                 @NotBlank String apellido,
                                 @Email @NotBlank String email
) {
}
