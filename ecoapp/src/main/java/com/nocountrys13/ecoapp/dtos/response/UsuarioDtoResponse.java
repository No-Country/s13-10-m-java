package com.nocountrys13.ecoapp.dtos.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UsuarioDtoResponse(@NotBlank UUID userId, @NotBlank String nombre,
                                 @NotBlank String apellido,
                                 @Email @NotBlank String email,
<<<<<<< HEAD
                                 @NotBlank Boolean validEmail ) {
	
=======
                                 @NotBlank Integer puntos
) {
>>>>>>> backend
}
