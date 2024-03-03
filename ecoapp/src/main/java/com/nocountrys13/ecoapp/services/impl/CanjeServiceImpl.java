package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.entities.Canje;
import com.nocountrys13.ecoapp.repositories.CanjeRepository;
import com.nocountrys13.ecoapp.repositories.PremioRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.security.JwtProvider;
import com.nocountrys13.ecoapp.services.ICanjeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CanjeServiceImpl implements ICanjeService {

    private final CanjeRepository canjeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PremioRepository premioRepository;
    private final JwtProvider jwtProvider;

    public void canjearPuntos(UUID premioId, String token) {

        var usuario = usuarioRepository
                .findById(jwtProvider.extractUserId(token))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        var premio = premioRepository
                .findById(premioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Premio no encontrado"));

        if(premio.getCantidad() == 0 || usuario.getPuntos() < premio.getPuntos())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Puntos insuficientes o stock agotado");

        var canje = new Canje();
        canje.setUsuario(usuario);
        canje.setPremio(premio);

        int puntosActuales = usuario.getPuntos() - premio.getPuntos();
        int cantidadDePremio = premio.getCantidad() - 1;

        usuario.setPuntos(puntosActuales);
        premio.setCantidad(cantidadDePremio);

        canjeRepository.save(canje);
        usuarioRepository.save(usuario);
        premioRepository.save(premio);
    }

}
