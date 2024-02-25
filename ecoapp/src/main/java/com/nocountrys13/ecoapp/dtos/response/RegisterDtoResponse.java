package com.nocountrys13.ecoapp.dtos.response;

import java.util.UUID;
import com.nocountrys13.ecoapp.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDtoResponse(
		@NotBlank UUID userId,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank @Email String email,
        @NotBlank @Email String imgUrl,
        @NotBlank @Email String jwtToken) {
	
	
	public RegisterDtoResponse(Usuario user, String imgUrl, String jwtToken) {
		this(user.getUserId()   ,user.getNombre(), user.getApellido(), user.getEmail(), imgUrl, jwtToken);
	}
	
}