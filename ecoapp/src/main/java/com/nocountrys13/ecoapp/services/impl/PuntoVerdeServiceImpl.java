package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.PuntoVerdeDto;
import com.nocountrys13.ecoapp.entities.Premio;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.services.PuntoVerdeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PuntoVerdeServiceImpl implements PuntoVerdeService {

    private final PuntoVerdeRepository puntoVerdeRepository;

    @Override
    public PuntoVerdeDto savePuntoVerde(PuntoVerdeDto puntoVerdeDto) {

        return puntoVerdeEntityADto(puntoVerdeRepository.save(puntoVerdeDtoAEntity(puntoVerdeDto))) ;
    }

    @Override
    public List<PuntoVerdeDto> getAllPuntosVerde() {
        var puntosVerdes = puntoVerdeRepository.findAll();
        return puntosVerdes.stream()
                .map(this::puntoVerdeEntityADto)
                .collect(Collectors.toList());
    }

    @Override
    public PuntoVerdeDto getPuntoVerdeById(UUID id) {
        var puntoVerde = puntoVerdeRepository.findById(id)
                .orElseThrow();

        return puntoVerdeEntityADto(puntoVerde);
    }


    @Override
    public void deletePuntoVerde(UUID id) {
        puntoVerdeRepository.deleteById(id);
    }

    @Override
    public PuntoVerdeDto updatePuntoVerde(UUID id, PuntoVerdeDto puntoVerdeDto) {
        var puntoVerdeEncontrado = puntoVerdeRepository.findById(id).orElseThrow();
        var puntoVerdeActualizar = puntoVerdeDtoAEntity(puntoVerdeDto);
        puntoVerdeActualizar.setPuntoVerdeId(id);
        return puntoVerdeEntityADto(puntoVerdeRepository.save(puntoVerdeActualizar));
    }

    private PuntoVerde puntoVerdeDtoAEntity(PuntoVerdeDto puntoVerdeDto) {
        return  PuntoVerde.builder()
                .nombrePv(puntoVerdeDto.nombre())
                .usuario(puntoVerdeDto.usuario())
                .direccion(puntoVerdeDto.direccion())
                .build();

    }

    private  PuntoVerdeDto puntoVerdeEntityADto(PuntoVerde puntoVerde){
        return new PuntoVerdeDto(puntoVerde.getNombrePv()
                ,puntoVerde.getDireccion(),puntoVerde.getUsuario());
    }

    @Override
    public void recibirReciclables(Reciclaje reciclaje) {

    }

    @Override
    public void modificarPremios(Premio premio) {

    }
}
