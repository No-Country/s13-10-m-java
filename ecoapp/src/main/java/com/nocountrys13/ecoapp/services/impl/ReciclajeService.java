package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.dtos.response.ReciclajeDtoResponse;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.repositories.ReciclajeRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IReciclajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReciclajeService implements IReciclajeService {

    private final ReciclajeRepository reciclajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PuntoVerdeRepository PuntoVerdeRepository;
    @Override
    public ReciclajeDtoResponse save(ReciclajeDTO newReciclaje, UUID idPuntoVerde) {

        if(Objects.isNull(newReciclaje))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Reciclaje no puede ser nulo.");

        Usuario actualUsuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        PuntoVerde puntoVerde = PuntoVerdeRepository.findById(idPuntoVerde).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "El punto verde con el id: " + idPuntoVerde+ " no existe"));

        Reciclaje reciclaje = new Reciclaje();
        BeanUtils.copyProperties(newReciclaje, reciclaje);
        reciclaje.setUsuario(actualUsuario);
        reciclaje.setPuntoVerde(puntoVerde);
        reciclajeRepository.save(reciclaje);

        return new ReciclajeDtoResponse(newReciclaje, reciclaje);
    }

    @Override
    public List<ReciclajeDtoResponse> getAll() {

        List<Reciclaje> listReciclaje = reciclajeRepository.findAll();

        return listReciclaje.stream()
                .map(
                        r -> {
                            Reciclaje reciclaje = new Reciclaje();

                            reciclaje.setTipoMateriales(r.getTipoMateriales());
                            reciclaje.setReciclajeId(r.getReciclajeId());
                            reciclaje.setCantidadCarton(r.getCantidadCarton());
                            reciclaje.setCantidadElectronico(r.getCantidadElectronico());
                            reciclaje.setCantidadMetal(r.getCantidadMetal());
                            reciclaje.setCantidadPapel(r.getCantidadPapel());
                            reciclaje.setCantidadVidrio(r.getCantidadVidrio());
                            reciclaje.setCantidadPlastico(r.getCantidadPlastico());
                            return new ReciclajeDtoResponse(reciclaje);
                        })
                .toList();
    }

    @Override
    public ReciclajeDtoResponse getReciclajeByID(UUID id) {

        var reciclajeEncontrado = reciclajeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reciclaje no encontrado con el id: " + id));

        Reciclaje reciclaje = new Reciclaje();
        BeanUtils.copyProperties(reciclajeEncontrado, reciclaje);

        return new ReciclajeDtoResponse(reciclaje);
    }

    @Override
    public ReciclajeDtoResponse update(UUID id, ReciclajeDTO updateReciclaje) {

        var reciclaje = reciclajeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Reciclaje no encontrado con el id: " + id));

        BeanUtils.copyProperties(updateReciclaje, reciclaje);

        return new ReciclajeDtoResponse(updateReciclaje, reciclaje);
    }

    @Override
    public boolean delete(UUID id) {
        if(reciclajeRepository.findById(id).isEmpty()){
            return false;
        }
        reciclajeRepository.deleteById(id);
        return true;
    }
}
