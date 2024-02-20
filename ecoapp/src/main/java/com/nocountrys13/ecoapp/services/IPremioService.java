package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.request.PremioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PremioDtoResponse;

import java.util.List;
import java.util.UUID;

public interface IPremioService {

    public PremioDtoResponse savePrize(PremioDtoRequest premioDtoRequest);

    public List<PremioDtoResponse> getAllPrize();

    public PremioDtoResponse getOnePrize(UUID id);

    public PremioDtoResponse updatePrize(UUID id, PremioDtoRequest premioDtoRequest);

    public String deletePrize(UUID id);

}
