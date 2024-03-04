package com.nocountrys13.ecoapp.dtos.request;

import com.nocountrys13.ecoapp.utils.Material;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.UUID;

public record CrearPuntoVerdeDtoRequest(
        @NotNull UUID userId,
        @NotBlank String nombre,
        @NotBlank @Size(max = 11) @Pattern(regexp = "^[0-9]+$") String dni,

        @NotBlank @Pattern(message = "Verificar que comience con nº5 y luego 10 digitos", regexp = "^5\\d{10}$") String telefono,
        @NotBlank @Size(max = 255) String horarioAtencion,
        @NotBlank @Size(max = 255) String diasAtencion,
        @NotEmpty List<Material> materialesAceptados,
        @NotNull String direccion,
        @NotNull String latitud,
        @NotNull String longitud
) {
}
