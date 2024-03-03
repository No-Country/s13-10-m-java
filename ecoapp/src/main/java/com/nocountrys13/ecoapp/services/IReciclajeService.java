package com.nocountrys13.ecoapp.services;


import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.dtos.response.ReciclajeUsuarioResponseDto;

import java.util.List;
import java.util.UUID;

public interface IReciclajeService {

   void save(ReciclajeDTO reciclajeDTO);

   List<ReciclajeUsuarioResponseDto> getAllRecicleByUserId(UUID userId);

}
