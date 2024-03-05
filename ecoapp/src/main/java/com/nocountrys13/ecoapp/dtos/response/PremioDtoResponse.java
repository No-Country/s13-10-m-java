package com.nocountrys13.ecoapp.dtos.response;

import com.nocountrys13.ecoapp.entities.Premio;

import java.util.UUID;

public record PremioDtoResponse(
        UUID premioId,
        String nombrePremio,
        String descripcion,
        Integer cantidad,
        Integer puntos,
        String imgUrl,
        UUID puntoVerdeId
) {
    public PremioDtoResponse(UUID premioId, String nombrePremio, String descripcion, Integer cantidad, Integer puntos, String imgUrl, UUID puntoVerdeId) {
        this.premioId = premioId;
        this.nombrePremio = nombrePremio;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.puntos = puntos;
        this.imgUrl = imgUrl;
        this.puntoVerdeId = puntoVerdeId;
    }

    public PremioDtoResponse(Premio premio) {
        this(
                premio.getPremioId(),
                premio.getNombrePremio(),
                premio.getDescripcion(),
                premio.getCantidad(),
                premio.getPuntos(),
                premio.getImgUrl(),
                premio.getPuntoVerde().getPuntoVerdeId()
        );
    }
}
