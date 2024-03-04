package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.PremioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PremioDtoResponse;
import com.nocountrys13.ecoapp.entities.Premio;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.repositories.PremioRepository;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.services.IPremioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PremioServiceImpl implements IPremioService {

    private final PremioRepository premioRepository;
    private final CloudinaryServiceImpl cloudinaryService;
    private final PuntoVerdeRepository puntoVerdeRepository;

    @Override
    public PremioDtoResponse savePrize(MultipartFile multipartFile, String nombrePremio, Integer cantidad, Integer puntos, UUID puntoVerdeId) throws IOException {
        Map<?, ?> result = cloudinaryService.upload(multipartFile);

        Optional<PuntoVerde> puntoVerde = puntoVerdeRepository.findById(puntoVerdeId);
        if (puntoVerde.isPresent()) {
            var puntoVerdeResp = puntoVerde.get();
            PremioDtoRequest premioDtoRequest = new PremioDtoRequest(puntoVerdeId, nombrePremio, cantidad, puntos);
            var premio = new Premio(premioDtoRequest);
            premio.setImgUrl((String) result.get("url"));
            premio.setPuntoVerde(puntoVerdeResp);
            premioRepository.save(premio);
            return new PremioDtoResponse(premio.getPremioId(), premio.getNombrePremio(), premio.getCantidad(), premio.getPuntos(), premio.getImgUrl(), premio.getPuntoVerde().getPuntoVerdeId());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Punto Verde no se encuentra registrado.");
    }

    @Override
    public List<PremioDtoResponse> getAllByPuntoVerdeId(UUID id) {
        return Optional
                .of(premioRepository.findAllByPuntoVerdePuntoVerdeId(id))
                .map(p -> p.stream().map(PremioDtoResponse::new).toList())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<PremioDtoResponse> getAllPrizes() {
        return Optional
                .of(premioRepository.findAll())
                .map(p -> p.stream().map(PremioDtoResponse::new).toList())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public PremioDtoResponse getOnePrize(UUID id) {
        Optional<Premio> premio = premioRepository.findById(id);
        if (premio.isPresent()) {
            var premioResp = premio.get();
            return new PremioDtoResponse(premioResp.getPremioId(), premioResp.getNombrePremio(), premioResp.getCantidad(), premioResp.getPuntos(), premioResp.getImgUrl(), premioResp.getPuntoVerde().getPuntoVerdeId());
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
            return new PremioDtoResponse(premio.getPremioId(), premio.getNombrePremio(), premio.getCantidad(), premio.getPuntos(), premio.getImgUrl(), premio.getPuntoVerde().getPuntoVerdeId());
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
