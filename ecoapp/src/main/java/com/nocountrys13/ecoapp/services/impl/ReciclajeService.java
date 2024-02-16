package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.ReciclajeDTO;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.repositories.ReciclajeRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IReciclajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReciclajeService implements IReciclajeService {

    private final ReciclajeRepository reciclajeRepository;
    private final UsuarioRepository usuarioRepository;
    @Override
    public Reciclaje save(ReciclajeDTO newReciclaje) {

        if(Objects.isNull(newReciclaje))
            throw new RuntimeException("El Reciclaje no puede ser nulo.");

        Usuario actualUsuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        //PuntoVerde puntoVerde = PuntoVerdeRepository.findByName(actualUsuario.getNombre());

        Reciclaje reciclaje = new Reciclaje();
        BeanUtils.copyProperties(newReciclaje, reciclaje);
        reciclaje.setUsuario(actualUsuario);
        //reciclaje.setPuntoVerde(puntoVerde);
        return reciclajeRepository.save(reciclaje);
    }

    @Override
    public List<Reciclaje> getAll() {
        return reciclajeRepository.findAll();
    }

    @Override
    public Reciclaje getReciclajeByID(UUID id) {

        var reciclajeId = reciclajeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Reciclaje no encontrado con el id: " + id));

        Reciclaje reciclaje = new Reciclaje();
        BeanUtils.copyProperties(reciclajeId, reciclaje);

        return reciclaje;
    }

    @Override
    public Reciclaje update(UUID id, ReciclajeDTO updateReciclaje) {

        var reciclaje = reciclajeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Reciclaje no encontrado con el id: " + id));

        BeanUtils.copyProperties(updateReciclaje, reciclaje);

        return reciclaje;
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
