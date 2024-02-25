package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.dtos.request.UpdatePuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDtoResponse;
import com.nocountrys13.ecoapp.services.IPuntoVerdeService;
import com.nocountrys13.ecoapp.utils.Material;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.nocountrys13.ecoapp.controllers.ApiConstant.*;

@RestController
@RequestMapping("/api/puntosVerde")
@RequiredArgsConstructor
@PreAuthorize(ROLE_USER)
public class PuntoVerdeController {

    private final IPuntoVerdeService IPuntoVerdeService;

    @GetMapping
    public ResponseEntity<?> getAllPuntosVerde(){
       return ResponseEntity.ok().body(IPuntoVerdeService.getAllPuntosVerde());
    }

    @PostMapping
    public ResponseEntity<?> savePuntoVerde(@RequestBody @Valid CrearPuntoVerdeDtoRequest crearPuntoVerdeDtoRequest){

        PuntoVerdeDtoResponse puntoVerdeDto = IPuntoVerdeService.savePuntoVerde(crearPuntoVerdeDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(puntoVerdeDto);
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getPuntosVerdeByUsuario(@PathVariable UUID id){
        return ResponseEntity.ok().body(IPuntoVerdeService.getPuntosVerdeByUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePuntoVerde(@PathVariable UUID id, @RequestBody @Valid UpdatePuntoVerdeDtoRequest puntoVerdeDto){
        return ResponseEntity.ok().body(IPuntoVerdeService.updatePuntoVerde(id, puntoVerdeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntoVerde(@PathVariable UUID id){
        IPuntoVerdeService.deletePuntoVerde(id);
        return ResponseEntity.ok().build();
    }



}
