package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.request.PremioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PremioDtoResponse;
import com.nocountrys13.ecoapp.services.IPremioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.nocountrys13.ecoapp.controllers.ApiConstant.ROLE_USER;

@RestController
@RequestMapping("/api/premio")
@RequiredArgsConstructor
@PreAuthorize(ROLE_USER)
public class PremioController {

    private final IPremioService premioService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> savePrize(
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("nombrePremio") String nombrePremio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam("puntos") Integer puntos,
            @RequestParam("puntoVerdeId") UUID puntoVerdeId
    ) {
        try {
            premioService.savePrize(imagen, nombrePremio, descripcion, cantidad, puntos, puntoVerdeId);
            return ResponseEntity.ok().body("Premio guardado.");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Verifica que la imagen no esté vacía.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PremioDtoResponse>> getAllByPuntoVerdeId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.getAllByPuntoVerdeId(id));
    }

    @GetMapping
    public ResponseEntity<List<PremioDtoResponse>> getAllPrizes() {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.getAllPrizes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PremioDtoResponse> updatePrize(@PathVariable(value = "id") UUID id,
                                                         @RequestBody @Valid PremioDtoRequest premioDtoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.updatePrize(id, premioDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.deletePrize(id));
    }
}
