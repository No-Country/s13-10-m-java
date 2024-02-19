package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.PremioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PremioDtoResponse;
import com.nocountrys13.ecoapp.entities.Premio;
import com.nocountrys13.ecoapp.repositories.PremioRepository;
import com.nocountrys13.ecoapp.services.IPremioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PremioServiceImpl implements IPremioService {

    private final PremioRepository premioRepository;

    @Override
    public PremioDtoResponse savePrize(PremioDtoRequest premioDtoRequest) {
        var premio = new Premio();
        BeanUtils.copyProperties(premioDtoRequest, premio);
        premio = premioRepository.save(premio);
        return new PremioDtoResponse(premio.getNombrePremio(), premio.getCantidad(), premio.getPuntos());
    }

    @Override
    public List<PremioDtoResponse> getAllPrize() {
        List<Premio> premios = premioRepository.findAll();
        if (premios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La lista de premios está vacía");
        }
        return premios.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PremioDtoResponse convertToDto(Premio premio) {
        return new PremioDtoResponse(premio.getNombrePremio(), premio.getCantidad(), premio.getPuntos());
    }

    @Override
    public PremioDtoResponse getOnePrize(UUID id) {
        Optional<Premio> premio = premioRepository.findById(id);
        if (premio.isPresent()) {
            var premioResp = premio.get();
            return new PremioDtoResponse(premioResp.getNombrePremio(), premioResp.getCantidad(), premioResp.getPuntos());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Premio no encontrado");
    }

    @Override
    public PremioDtoResponse updatePrize(UUID id, PremioDtoRequest premioDtoRequest) {
        Optional<Premio> premioBuscado = premioRepository.findById(id);
        if (premioBuscado.isPresent()) {
            var premio = premioBuscado.get();
            BeanUtils.copyProperties(premioDtoRequest, premio);
            premio = premioRepository.save(premio);
            return new PremioDtoResponse(premio.getNombrePremio(), premio.getCantidad(), premio.getPuntos());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el premio buscado");
    }

    @Override
    public String deletePrize(UUID id) {
        Optional<Premio> premio = premioRepository.findById(id);
        if (premio.isPresent()) {
            premioRepository.delete(premio.get());
            return "El premio fue eliminado";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el premio buscado");
    }

}
