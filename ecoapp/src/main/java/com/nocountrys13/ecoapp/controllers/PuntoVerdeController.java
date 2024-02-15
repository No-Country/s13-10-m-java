package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.PuntoVerdeDto;
import com.nocountrys13.ecoapp.services.PuntoVerdeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
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
    public ResponseEntity<?> savePuntoVerde(@RequestBody PuntoVerdeDto puntoVerdeDto){
        puntoVerdeService.savePuntoVerde(puntoVerdeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(puntoVerdeService.savePuntoVerde(puntoVerdeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPuntoVerde (@PathVariable UUID id){
        puntoVerdeService.getPuntoVerdeById(id);
        return ResponseEntity.ok().body(puntoVerdeService.getPuntoVerdeById(id)) ;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntoVerde(@PathVariable UUID id){
        puntoVerdeService.deletePuntoVerde(id);
        return ResponseEntity.ok().build();
    }




}
