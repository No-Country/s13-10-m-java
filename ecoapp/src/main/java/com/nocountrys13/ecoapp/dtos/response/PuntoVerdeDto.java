package com.nocountrys13.ecoapp.dtos.response;

import com.nocountrys13.ecoapp.entities.Usuario;
import jakarta.validation.constraints.NotBlank;

public record PuntoVerdeDto(@NotBlank String nombre, @NotBlank String direccion) {
}
