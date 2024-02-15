package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDto;
import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDto;
import com.nocountrys13.ecoapp.services.PuntoVerdeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/puntosVerde")
@RequiredArgsConstructor
public class PuntoVerdeController {

    private final PuntoVerdeService puntoVerdeService;

    @GetMapping
    public ResponseEntity<?> getAllPuntosVerde(){
       return ResponseEntity.ok().body(puntoVerdeService.getAllPuntosVerde());
    }

    @PostMapping
    public ResponseEntity<?> savePuntoVerde(@RequestBody CrearPuntoVerdeDto crearPuntoVerdeDto){

        PuntoVerdeDto puntoVerdeDto = puntoVerdeService.savePuntoVerde(crearPuntoVerdeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(puntoVerdeDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPuntoVerde (@PathVariable UUID id){
        puntoVerdeService.getPuntoVerdeById(id);
        return ResponseEntity.ok().body(puntoVerdeService.getPuntoVerdeById(id)) ;
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getPuntosVerdeByUsuario(@PathVariable UUID id){
        return ResponseEntity.ok().body(puntoVerdeService.getPuntosVerdeByUsuario(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntoVerde(@PathVariable UUID id){
        puntoVerdeService.deletePuntoVerde(id);
        return ResponseEntity.ok().build();
    }




}
