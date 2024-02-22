package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.dtos.response.ReciclajeDtoResponse;
import com.nocountrys13.ecoapp.entities.Reciclaje;

import java.util.List;
import java.util.UUID;

public interface IReciclajeService {

    ReciclajeDtoResponse save(ReciclajeDTO newReciclaje, UUID idPuntoVerde);
    List<ReciclajeDtoResponse> getAll();
    ReciclajeDtoResponse getReciclajeByID(UUID idReciclaje);

    ReciclajeDtoResponse update(UUID id, ReciclajeDTO updateReciclaje);

    boolean delete(UUID id);
}
