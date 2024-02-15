package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.PremioDto;
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

@Service
@RequiredArgsConstructor
public class PremioServiceImpl implements IPremioService {

    private final PremioRepository premioRepository;

    @Override
    public Premio savePrize(PremioDto premioDto) {
        var premio = new Premio();
        BeanUtils.copyProperties(premioDto, premio);
        return premioRepository.save(premio);
    }

    @Override
    public List<Premio> getAllPrize() {
        List<Premio> premios = premioRepository.findAll();
        if (!premios.isEmpty()) {
            return premios;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La lista de premios está vacía");
    }

    @Override
    public Premio getOnePrize(UUID id) {
        Optional<Premio> premio = premioRepository.findById(id);
        if (premio.isPresent()) {
            return premio.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Premio no encontrado");
    }

    @Override
    public Premio updatePrize(UUID id, PremioDto premioDto) {
        Optional<Premio> premioBuscado = premioRepository.findById(id);
        if (premioBuscado.isPresent()) {
            var premio = premioBuscado.get();
            BeanUtils.copyProperties(premioDto, premio);
            return premioRepository.save(premio);
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
