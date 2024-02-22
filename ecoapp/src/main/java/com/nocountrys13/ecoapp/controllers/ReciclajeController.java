package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.dtos.response.ReciclajeDtoResponse;
import com.nocountrys13.ecoapp.entities.Reciclaje;
import com.nocountrys13.ecoapp.services.IReciclajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reciclaje")
@RequiredArgsConstructor
public class ReciclajeController {

    private final IReciclajeService iReciclajeService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveVehicle(@Valid @RequestBody ReciclajeDTO newReciclajeDTO, String emailUsuario, UUID idPuntoVerde){
        try {
            return new ResponseEntity<>(iReciclajeService.save(newReciclajeDTO, emailUsuario, idPuntoVerde), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el objeto reciclaje:" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
/*
    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(iReciclajeService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error al traer todos los reciclajes", HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping("/{idUsuario}")
    public ResponseEntity<?> AllReciclajesByIdUsuario(@PathVariable UUID idUsuario){
        try {
            return new ResponseEntity<>(iReciclajeService.getAllReciclajeByIdUsuario(idUsuario), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error al encontrar los Reciclajes", HttpStatus.BAD_REQUEST);
        }
    }

/*    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid ReciclajeDTO reciclajeDTO){
        try {
            ReciclajeDtoResponse updateReciclaje = iReciclajeService.update(id, reciclajeDTO);
            return new ResponseEntity<>("Reciclaje actualizado exitosamente, "+ updateReciclaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error al actualizar el reciclaje", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return new ResponseEntity<>(this.iReciclajeService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }*/
}
