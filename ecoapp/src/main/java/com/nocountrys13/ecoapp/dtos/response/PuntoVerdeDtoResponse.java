package com.nocountrys13.ecoapp.dtos.response;

import com.nocountrys13.ecoapp.entities.Reciclaje;
import com.nocountrys13.ecoapp.utils.Material;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.UUID;

public record PuntoVerdeDtoResponse(


        @NotNull UUID puntoVerdeId,

        @NotBlank String nombre,
        @NotBlank @Size(max = 255) String horarioAtencion,
        @NotBlank @Size(max = 255) String diasAtencion,

        @NotBlank String latitud,
        @NotBlank String longitud,
        @NotBlank String direccion,
        @NotBlank @Pattern(regexp = "^549\\d{10}$") String telefono,
        @NotEmpty List<Material> materialesAceptados,
         List<ReciclajeResponseDto> reciclajes


) {

}
