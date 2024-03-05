package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.request.PremioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PremioDtoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IPremioService {

    public PremioDtoResponse savePrize(MultipartFile multipartFile, String nombrePremio, String descripcion, Integer cantidad, Integer puntos, UUID puntoVerdeId) throws IOException;

    public List<PremioDtoResponse> getAllByPuntoVerdeId(UUID id);

    public List<PremioDtoResponse> getAllPrizes();

    public PremioDtoResponse getOnePrize(UUID id);

    public PremioDtoResponse updatePrize(UUID id, PremioDtoRequest premioDtoRequest);

    public String deletePrize(UUID id);

}
