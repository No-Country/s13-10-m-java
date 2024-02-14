package com.nocountrys13.ecoapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
     @NotBlank String email,
     @NotBlank @Email String password
) {}
