package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.dtos.request.UpdatePuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDtoResponse;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IPuntoVerdeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PuntoVerdeServiceImpl implements IPuntoVerdeService {

    private final PuntoVerdeRepository puntoVerdeRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public PuntoVerdeDtoResponse savePuntoVerde(CrearPuntoVerdeDtoRequest crearPuntoVerdeDtoRequest) {
        Usuario usuario = usuarioRepository.findById(crearPuntoVerdeDtoRequest.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe"));
        PuntoVerde puntoVerde = new PuntoVerde(crearPuntoVerdeDtoRequest.nombre(),
                crearPuntoVerdeDtoRequest.latitud(), crearPuntoVerdeDtoRequest.longitud(),
                crearPuntoVerdeDtoRequest.telefono(), crearPuntoVerdeDtoRequest.dni(),
                crearPuntoVerdeDtoRequest.horarioAtencion(), crearPuntoVerdeDtoRequest.diasAtencion(),
                crearPuntoVerdeDtoRequest.direccion(), crearPuntoVerdeDtoRequest.materialesAceptados(),usuario);

        puntoVerdeRepository.save(puntoVerde);
        return puntoVerdeEntityADtoResponse(puntoVerde);
    }

    @Override
    public PuntoVerdeDtoResponse getPuntoVerdeById(UUID id) {
        return puntoVerdeEntityADtoResponse(findById(id));
    }

    @Override
    public List<PuntoVerdeDtoResponse> getAllPuntosVerde() {
        var puntosVerdes = puntoVerdeRepository.findAll();
        if (puntosVerdes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron puntos verdes.");
        }
        return puntosVerdes.stream()
                .map(this::puntoVerdeEntityADtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePuntoVerde(UUID id) {
            var puntoVerde = findById(id);
            puntoVerdeRepository.delete(puntoVerde);
    }

    @Override
    public PuntoVerdeDtoResponse updatePuntoVerde(UUID id, UpdatePuntoVerdeDtoRequest puntoVerdeDto) {

            var puntoVerdeEncontrado = findById(id);
            puntoVerdeEncontrado.setNombrePv(puntoVerdeDto.nombre());
            puntoVerdeEncontrado.setTelefono(puntoVerdeDto.telefono());
            puntoVerdeEncontrado.setMaterialesAceptados(puntoVerdeDto.materialesAceptados());
            puntoVerdeEncontrado.setHorariosAtencion(puntoVerdeDto.horarioAtencion());
            puntoVerdeEncontrado.setDiasAtencion(puntoVerdeDto.diasAtencion());
            puntoVerdeEncontrado.setLatitud(puntoVerdeDto.latitud());
            puntoVerdeEncontrado.setLongitud(puntoVerdeDto.longitud());

            return puntoVerdeEntityADtoResponse(puntoVerdeRepository.save(puntoVerdeEncontrado));

    }

    @Override
    public List<PuntoVerdeDtoResponse> getPuntosVerdeByUsuario(UUID id) {
        List<PuntoVerde> puntosVerde = puntoVerdeRepository.findAllByUsuarioUserId(id);
        if (puntosVerde.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron puntos verdes para el usuario.");
        }
        return puntosVerde.stream()
                .map(this::puntoVerdeEntityADtoResponse)
                .collect(Collectors.toList());
    }

    private PuntoVerde findById(UUID id) {
        return puntoVerdeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontro el punto verde con el id: " + id.toString() + "."));
    }

    private PuntoVerdeDtoResponse puntoVerdeEntityADtoResponse(PuntoVerde puntoVerde) {
        return new PuntoVerdeDtoResponse(puntoVerde.getPuntoVerdeId(), puntoVerde.getNombrePv(),
                puntoVerde.getHorariosAtencion(), puntoVerde.getDiasAtencion(), puntoVerde.getLatitud(),
                puntoVerde.getLongitud(), puntoVerde.getDireccion(), puntoVerde.getTelefono(),
                puntoVerde.getMaterialesAceptados());
    }

}
