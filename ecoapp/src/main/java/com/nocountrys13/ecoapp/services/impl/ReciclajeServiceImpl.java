package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.repositories.ReciclajeRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IReciclajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReciclajeServiceImpl implements IReciclajeService {

    private final ReciclajeRepository reciclajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PuntoVerdeRepository puntoVerdeRepository;

    @Override
    public void save(ReciclajeDTO reciclajeDTO) {
        var usuario = usuarioRepository.findByEmail(reciclajeDTO.emailUsuario());
        var puntoVerde = puntoVerdeRepository.findById(reciclajeDTO.idPuntoVerde()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var reciclaje = new Reciclaje();
        BeanUtils.copyProperties(reciclajeDTO, reciclaje);
        reciclaje.setUsuario(usuario);
        reciclaje.setPuntoVerde(puntoVerde);
        reciclajeRepository.save(reciclaje);
    }
}
