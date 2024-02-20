package com.nocountrys13.ecoapp.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ReciclajeDTO(
        @NotEmpty @NotBlank String tipoMateriales,
        Integer cantidadPlastico,
        Integer cantidadPapel,
        Integer cantidadCarton,
        Integer cantidadVidrio,
        Integer cantidadMetal,
        Integer cantidadElectronico
) {
}
