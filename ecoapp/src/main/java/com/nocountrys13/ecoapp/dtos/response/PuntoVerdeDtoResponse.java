package com.nocountrys13.ecoapp.dtos.response;

import com.nocountrys13.ecoapp.utils.Material;

import java.util.List;
import java.util.UUID;

public record PuntoVerdeDtoResponse(
        UUID puntoVerdeId,
        String nombre,
        String horarioAtencion,
        String diasAtencion,
        String latitud,
        String longitud,
        String direccion,
        String telefono,
        List<Material> materialesAceptados,
        List<ReciclajeResponseDto> reciclajes
) {
}
