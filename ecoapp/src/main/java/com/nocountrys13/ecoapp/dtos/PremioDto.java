package com.nocountrys13.ecoapp.dtos;

import jakarta.validation.constraints.NotBlank;

public record PremioDto(@NotBlank String nombrePremio,
                        @NotBlank Integer cantidad,

                        @NotBlank Integer puntos) {
}
