package com.nocountrys13.ecoapp.dtos;

import com.nocountrys13.ecoapp.entities.Direccion;
import com.nocountrys13.ecoapp.entities.Usuario;

import java.util.UUID;

public record PuntoVerdeDto(String nombre, String direccion, Usuario usuario) {
}
