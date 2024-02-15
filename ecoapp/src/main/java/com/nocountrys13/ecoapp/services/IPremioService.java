package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.PremioDto;
import com.nocountrys13.ecoapp.entities.Premio;

import java.util.List;
import java.util.UUID;

public interface IPremioService {

    public Premio savePrize(PremioDto premioDto);

    public List<Premio> getAllPrize();

    public Premio getOnePrize(UUID id);

    public Premio updatePrize(UUID id, PremioDto premioDto);

    public String deletePrize(UUID id);

}
