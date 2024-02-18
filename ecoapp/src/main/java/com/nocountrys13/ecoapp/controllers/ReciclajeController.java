package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.ReciclajeDTO;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import com.nocountrys13.ecoapp.services.IReciclajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reciclaje")
@RequiredArgsConstructor
public class ReciclajeController {

    private final IReciclajeService iReciclajeService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveVehicle(@Valid @RequestBody ReciclajeDTO newReciclajeDTO){
        try {
            return new ResponseEntity<>(iReciclajeService.save(newReciclajeDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el objeto reciclaje:" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(iReciclajeService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error al traer todos los reciclajes", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> ReciclajeById(@PathVariable UUID id){
        try {
            var reciclajeById = iReciclajeService.getReciclajeByID(id);
            return new ResponseEntity<>("Reciclaje encontrado exitosamente, "+ reciclajeById, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error al encontrar el Reciclaje", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid ReciclajeDTO reciclajeDTO){
        try {
            Reciclaje updateReciclaje = iReciclajeService.update(id, reciclajeDTO);
            return new ResponseEntity<>("Reciclaje actualizado exitosamente, "+ updateReciclaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error al actualizar el reciclaje", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return new ResponseEntity<>(this.iReciclajeService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
