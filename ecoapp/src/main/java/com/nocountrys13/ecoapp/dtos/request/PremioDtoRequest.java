package com.nocountrys13.ecoapp.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record PremioDtoRequest(@NotBlank String nombrePremio,
                               @NotBlank Integer cantidad,

                               @NotBlank Integer puntos) {
}
