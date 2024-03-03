package com.nocountrys13.ecoapp.dtos.response;

import java.util.UUID;

public record UsuarioDtoResponse(
        UUID userId,
        String nombre,
        String apellido,
        String email,
        boolean validEmail,
        Integer puntos,
        String imgUrl
) {
}
