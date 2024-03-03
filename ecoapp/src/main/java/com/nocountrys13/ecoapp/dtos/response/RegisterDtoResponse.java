package com.nocountrys13.ecoapp.dtos.response;

import java.util.UUID;

import com.nocountrys13.ecoapp.entities.Usuario;

public record RegisterDtoResponse(
        UUID userId,
        String nombre,
        String apellido,
        String email,
        String imgUrl,
        String jwtToken) {
    public RegisterDtoResponse(Usuario user, String imgUrl, String jwtToken) {
        this(user.getUserId(), user.getNombre(), user.getApellido(), user.getEmail(), imgUrl, jwtToken);
    }
}