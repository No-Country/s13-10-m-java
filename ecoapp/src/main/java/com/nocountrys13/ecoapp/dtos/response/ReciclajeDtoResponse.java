package com.nocountrys13.ecoapp.dtos.response;

import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.entities.Reciclaje;

import java.util.UUID;

public record ReciclajeDtoResponse(
        UUID idReciclaje,
        String tipoMateriales,
        Integer cantidadPlastico,
        Integer cantidadPapel,
        Integer cantidadCarton,
        Integer cantidadVidrio,
        Integer cantidadMetal,
        Integer cantidadElectronico){
    public ReciclajeDtoResponse(ReciclajeDTO dto, Reciclaje reciclaje){
        this(reciclaje.getReciclajeId(), dto.tipoMateriales(), dto.cantidadPlastico(), dto.cantidadPapel(),
                dto.cantidadCarton(), dto.cantidadVidrio(), dto.cantidadMetal(), dto.cantidadElectronico());
    }
    public ReciclajeDtoResponse( Reciclaje reciclaje){
        this(reciclaje.getReciclajeId(), reciclaje.getTipoMateriales(), reciclaje.getCantidadPlastico(), reciclaje.getCantidadPapel(),
                reciclaje.getCantidadCarton(), reciclaje.getCantidadVidrio(), reciclaje.getCantidadMetal(), reciclaje.getCantidadElectronico());
    }
}
