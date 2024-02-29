package com.nocountrys13.ecoapp.dtos.response;

import com.nocountrys13.ecoapp.utils.Material;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ReciclajeResponseDto(
   UUID reciclajeId,
   List<Material> materialesRecibidos,
   String descripcion,
   String email,
   LocalDate dia
) {}
