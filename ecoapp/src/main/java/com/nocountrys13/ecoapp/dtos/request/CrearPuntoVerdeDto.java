package com.nocountrys13.ecoapp.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CrearPuntoVerdeDto(@NotBlank UUID userId, @NotBlank String nombre , @NotBlank String direccion) {
}
