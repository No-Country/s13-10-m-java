package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.AuthenticationDTO;
import com.nocountrys13.ecoapp.dtos.Jwt;
import com.nocountrys13.ecoapp.dtos.RegisterDTO;

public interface IAuthenticationService {

   void register(RegisterDTO dto);
   Jwt login(AuthenticationDTO dto);

}
