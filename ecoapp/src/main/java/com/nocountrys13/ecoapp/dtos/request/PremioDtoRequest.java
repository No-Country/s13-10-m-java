package com.nocountrys13.ecoapp.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PremioDtoRequest(
        @NotBlank UUID puntoVerdeId,
        @NotBlank String nombrePremio,
        @NotBlank Integer cantidad,
        @NotBlank Integer puntos
) {
    public PremioDtoRequest(UUID puntoVerdeId, String nombrePremio, Integer cantidad, Integer puntos) {
        this.puntoVerdeId = puntoVerdeId;
        this.nombrePremio = nombrePremio;
        this.cantidad = cantidad;
        this.puntos = puntos;
    }
}
