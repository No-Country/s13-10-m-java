package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.dtos.response.ReciclajeUsuarioResponseDto;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReciclajeServiceImpl implements IReciclajeService {

    private final ReciclajeRepository reciclajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PuntoVerdeRepository puntoVerdeRepository;

    @Override
    public void save(ReciclajeDTO reciclajeDTO) {
        var usuario = usuarioRepository.findByEmail(reciclajeDTO.emailUsuario());
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email de usuario no encontrado.");
        }
        Integer puntos = usuario.getPuntos() + 10;
        usuario.setPuntos(puntos);

        var reciclaje = new Reciclaje();
        BeanUtils.copyProperties(reciclajeDTO, reciclaje);
        reciclaje.setUsuario(usuario);
        reciclaje.setDia(LocalDate.now());
        reciclajeRepository.save(reciclaje);

        var puntoVerde = puntoVerdeRepository.findById(reciclajeDTO.idPuntoVerde()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        puntoVerde.getListadoReciclaje().add(reciclaje);
        puntoVerdeRepository.save(puntoVerde);
    }

    @Override
    public List<ReciclajeUsuarioResponseDto> getAllRecicleByUserId(UUID userId) {
        try {
            return reciclajeRepository.findAllByUsuarioUserId(userId).stream()
                    .map(r -> new ReciclajeUsuarioResponseDto(
                            r.getReciclajeId(),
                            r.getMaterialesRecibidos(),
                            r.getDescripcion(),
                            r.getUsuario().getEmail(),
                            r.getDia(),
                            r.getUsuario().getUserId(),
                            r.getIdPuntoVerde().getNombrePv()
                    )).toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La lista de reciclados se encuentra vacía. Verifica el Id del usuario.");
        }
    }
}
