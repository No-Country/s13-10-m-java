package com.nocountrys13.ecoapp.dtos.response;

import jakarta.validation.constraints.NotBlank;

public record PremioDtoResponse(@NotBlank String nombrePremio,
                                @NotBlank Integer cantidad,

                                @NotBlank Integer puntos) {
}
