package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.dtos.request.UpdatePuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.services.IPuntoVerdeService;
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

    private final IPuntoVerdeService puntoVerdeService;

    @GetMapping
    public ResponseEntity<?> getAllPuntosVerde(){
       return ResponseEntity.ok().body(puntoVerdeService.getAllPuntosVerde());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPuntoVerdeById(@PathVariable UUID id){
        return ResponseEntity.ok(puntoVerdeService.getPuntoVerdeById(id));
    }

    @PostMapping
    public ResponseEntity<?> savePuntoVerde(@RequestBody @Valid CrearPuntoVerdeDtoRequest crearPuntoVerdeDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(puntoVerdeService.savePuntoVerde(crearPuntoVerdeDtoRequest));
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getPuntosVerdeByUsuario(@PathVariable UUID id){
        return ResponseEntity.ok().body(puntoVerdeService.getPuntosVerdeByUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePuntoVerde(@PathVariable UUID id, @RequestBody @Valid UpdatePuntoVerdeDtoRequest puntoVerdeDto){
        return ResponseEntity.ok().body(puntoVerdeService.updatePuntoVerde(id, puntoVerdeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntoVerde(@PathVariable UUID id){
        puntoVerdeService.deletePuntoVerde(id);
        return ResponseEntity.ok().build();
    }



}
