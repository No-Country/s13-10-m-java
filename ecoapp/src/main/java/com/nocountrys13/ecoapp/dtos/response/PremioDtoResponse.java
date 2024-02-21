package com.nocountrys13.ecoapp.dtos.response;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PremioDtoResponse(@NotBlank UUID premioId, @NotBlank String nombrePremio,
                                @NotBlank Integer cantidad,

                                @NotBlank Integer puntos) {
}
