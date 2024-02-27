package com.nocountrys13.ecoapp.dtos.request;

import com.nocountrys13.ecoapp.utils.Material;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record ReciclajeDTO(
                @NotBlank
                String emailUsuario,
                @NotBlank
                List<Material> materialesRecibidos,
                @NotBlank
                String descripcion,
                @NotBlank
                UUID idPuntoVerde

) {
}
