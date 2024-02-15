package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDto;
import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDto;
import com.nocountrys13.ecoapp.entities.Premio;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PuntoVerdeService {
   PuntoVerdeDto savePuntoVerde(CrearPuntoVerdeDto crearPuntoVerdeDto);

   List<PuntoVerdeDto> getAllPuntosVerde();

    PuntoVerdeDto getPuntoVerdeById(UUID id);

    PuntoVerdeDto updatePuntoVerde(UUID id , PuntoVerdeDto puntoVerdeDto);

    List<PuntoVerdeDto> getPuntosVerdeByUsuario(UUID id);

    void deletePuntoVerde(UUID id);

    void recibirReciclables(Reciclaje reciclaje); //cambiarlo a dto despues

    void modificarPremios(Premio premio); ////cambiarlo a dto despues



}
