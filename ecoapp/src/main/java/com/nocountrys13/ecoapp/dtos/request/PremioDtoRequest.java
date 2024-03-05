package com.nocountrys13.ecoapp.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PremioDtoRequest(
        @NotBlank UUID puntoVerdeId,
        @NotBlank String nombrePremio,
        @NotBlank String descripcion,
        @NotBlank Integer cantidad,
        @NotBlank Integer puntos
) {
    public PremioDtoRequest(UUID puntoVerdeId, String nombrePremio, String descripcion, Integer cantidad, Integer puntos) {
        this.puntoVerdeId = puntoVerdeId;
        this.nombrePremio = nombrePremio;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.puntos = puntos;
    }
}
