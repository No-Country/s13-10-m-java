package com.nocountrys13.ecoapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO (
     @NotBlank String nombre,
     @NotBlank String apellido,
     @NotBlank @Email String email,
     @NotBlank String password
) {}
