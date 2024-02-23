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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReciclajeService implements IReciclajeService {

    private final ReciclajeRepository reciclajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PuntoVerdeRepository PuntoVerdeRepository;
    @Override
    public ReciclajeDtoResponse save(ReciclajeDTO newReciclaje, String emailUsuario, UUID idPuntoVerde) {

        if(Objects.isNull(newReciclaje))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Reciclaje no puede ser nulo.");

        Usuario usuarioEncontrado = usuarioRepository.findByEmail(emailUsuario);

        if(Objects.isNull(usuarioEncontrado))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con el email: " + emailUsuario + " no existe");

        int puntos = usuarioEncontrado.getPuntos() + 5;
        usuarioEncontrado.setPuntos(puntos);
        usuarioRepository.save(usuarioEncontrado);

        PuntoVerde puntoVerde = PuntoVerdeRepository.findById(idPuntoVerde).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "El punto verde con el id: " + idPuntoVerde+ " no existe"));

        Reciclaje reciclaje = new Reciclaje();
        BeanUtils.copyProperties(newReciclaje, reciclaje);
        reciclaje.setUsuario(usuarioEncontrado);
        reciclaje.setPuntoVerde(puntoVerde);
        reciclajeRepository.save(reciclaje);

        return new ReciclajeDtoResponse(newReciclaje, reciclaje);
    }

    @Override
    public List<ReciclajeDtoResponse> getAllReciclajeByIdUsuario(UUID idUsuario) {

        usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new IllegalArgumentException("Reciclaje no encontrado con el id: " + idUsuario));

        List<Reciclaje> listReciclaje = reciclajeRepository.getReciclajeByUsuario_UserId(idUsuario);

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

}
