package com.nocountrys13.ecoapp.dtos.request;

import com.nocountrys13.ecoapp.utils.Material;
import jakarta.validation.constraints.*;

import java.util.List;

public record UpdatePuntoVerdeDtoRequest(
        @NotBlank String nombre,
        @NotBlank @Pattern(regexp = "^5\\d{10}$") String telefono,
        @NotEmpty List<Material> materialesAceptados,
        @NotBlank @Size(max = 255) String horarioAtencion,
        @NotBlank @Size(max = 255) String diasAtencion,
        @NotBlank String direccion,

        @NotBlank String latitud,
        @NotBlank String longitud

) {
}
