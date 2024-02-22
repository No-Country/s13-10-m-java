package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.dtos.request.UpdatePuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDtoResponse;
import com.nocountrys13.ecoapp.utils.Material;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IPuntoVerdeService {
    PuntoVerdeDtoResponse savePuntoVerde(CrearPuntoVerdeDtoRequest crearPuntoVerdeDtoRequest);

    List<PuntoVerdeDtoResponse> getAllPuntosVerde();

    PuntoVerdeDtoResponse updatePuntoVerde(UUID id , UpdatePuntoVerdeDtoRequest puntoVerdeDto);

    List<PuntoVerdeDtoResponse> getPuntosVerdeByUsuario(UUID id);

    void deletePuntoVerde(UUID id);





}
