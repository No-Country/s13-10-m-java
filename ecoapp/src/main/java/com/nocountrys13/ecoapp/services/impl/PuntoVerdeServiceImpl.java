package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDto;
import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDto;
import com.nocountrys13.ecoapp.entities.Premio;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.PuntoVerdeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PuntoVerdeServiceImpl implements PuntoVerdeService {

    private final PuntoVerdeRepository puntoVerdeRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public PuntoVerdeDto savePuntoVerde(CrearPuntoVerdeDto crearPuntoVerdeDto) {
        Usuario usuario = usuarioRepository.findById(crearPuntoVerdeDto.userId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe"));
        PuntoVerde  puntoVerde = new PuntoVerde();
        puntoVerde.setUsuario(usuario);
        puntoVerde.setNombrePv(crearPuntoVerdeDto.nombre());
        puntoVerde.setDireccion(crearPuntoVerdeDto.direccion());

        puntoVerdeRepository.save(puntoVerde);
        return puntoVerdeEntityADto(puntoVerde);
    }

    @Override
    public List<PuntoVerdeDto> getAllPuntosVerde() {
        var puntosVerdes = puntoVerdeRepository.findAll();
        if(puntosVerdes.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron puntos verdes.");
        }
        return puntosVerdes.stream()
                .map(this::puntoVerdeEntityADto)
                .collect(Collectors.toList());
    }

    @Override
    public PuntoVerdeDto getPuntoVerdeById(UUID id) {
        var puntoVerde = findById(id);

        return puntoVerdeEntityADto(puntoVerde);
    }


    @Override
    public void deletePuntoVerde(UUID id) {
        try {
            var puntoVerde = findById(id);
            puntoVerdeRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    private PuntoVerde findById(UUID id) {
        return puntoVerdeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontro el punto verde con el id: "+id.toString() + "."));
    }

    @Override
    public PuntoVerdeDto updatePuntoVerde(UUID id, PuntoVerdeDto puntoVerdeDto) {
        var puntoVerdeEncontrado = findById(id);

        var puntoVerdeActualizar = puntoVerdeDtoAEntity(puntoVerdeDto);
        puntoVerdeActualizar.setPuntoVerdeId(id);
        return puntoVerdeEntityADto(puntoVerdeRepository.save(puntoVerdeActualizar));
    }

    @Override
    public List<PuntoVerdeDto> getPuntosVerdeByUsuario(UUID id) {
        List<PuntoVerde> puntosVerde = puntoVerdeRepository.findAllByUsuarioUserId(id);
        if(puntosVerde.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron puntos verdes para el usuario.");
        }
        return puntosVerde.stream()
                .map(this::puntoVerdeEntityADto)
                .collect(Collectors.toList());
    }

    private PuntoVerde puntoVerdeDtoAEntity(PuntoVerdeDto puntoVerdeDto) {
        var puntoVerde = new PuntoVerde();
        puntoVerde.setNombrePv(puntoVerdeDto.nombre());

        puntoVerde.setDireccion(puntoVerdeDto.direccion());
        return puntoVerde;
    }

    private  PuntoVerdeDto puntoVerdeEntityADto(PuntoVerde puntoVerde){
        return new PuntoVerdeDto(puntoVerde.getNombrePv()
                ,puntoVerde.getDireccion());
    }

}
