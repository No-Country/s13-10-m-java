package com.nocountrys13.ecoapp.dtos.request;

import com.nocountrys13.ecoapp.utils.Material;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record ReciclajeDTO(
        @NotBlank
        String emailUsuario,
        @Size(min = 1, message = "La cantidad de materiales no puede estar vacía") @NotNull
        List<Material> materialesRecibidos,
        @NotBlank
        String descripcion,
        @NotNull
        UUID idPuntoVerde
) {
}
