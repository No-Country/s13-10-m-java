package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.ReciclajeDTO;
import com.nocountrys13.ecoapp.entities.Reciclaje;

import java.util.List;
import java.util.UUID;

public interface IReciclajeService {

    Reciclaje save(ReciclajeDTO newReciclaje);
    List<Reciclaje> getAll();
    Reciclaje getReciclajeByID(UUID idReciclaje);

    Reciclaje update(UUID id, ReciclajeDTO updateReciclaje);

    boolean delete(UUID id);
}
