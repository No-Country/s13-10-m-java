package com.nocountrys13.ecoapp.services;


import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.dtos.response.ReciclajeResponseDto;
import com.nocountrys13.ecoapp.dtos.response.ReciclajeUsuarioResponseDto;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IReciclajeService {

   void save(ReciclajeDTO reciclajeDTO);

   List<ReciclajeUsuarioResponseDto> getAllRecicleByUserId(UUID userId);

}
