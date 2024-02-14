package com.nocountrys13.ecoapp.dtos.response;

import com.nocountrys13.ecoapp.dtos.request.RegisterDtoRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDtoResponse(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank @Email String email,
        @NotBlank @Email String imgUrl,
        @NotBlank @Email String jwtToken) {
	
	
	public RegisterDtoResponse(RegisterDtoRequest dto, String imgUrl, String jwtToken) {
		this(dto.nombre(), dto.apellido(), dto.email(), imgUrl, jwtToken);
	}
	
}