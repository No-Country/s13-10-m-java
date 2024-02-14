package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.AuthenticationDTO;
import com.nocountrys13.ecoapp.dtos.Jwt;
import com.nocountrys13.ecoapp.dtos.request.RegisterDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.RegisterDtoResponse;

public interface IAuthenticationService {

    public RegisterDtoResponse register(RegisterDtoRequest dto);
    Jwt login(AuthenticationDTO dto);

}
