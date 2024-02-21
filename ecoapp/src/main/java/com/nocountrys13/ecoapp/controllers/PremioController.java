package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.request.PremioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PremioDtoResponse;
import com.nocountrys13.ecoapp.entities.Premio;
import com.nocountrys13.ecoapp.services.IPremioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/premio")
@RequiredArgsConstructor
public class PremioController {

    private final IPremioService premioService;

    @PostMapping
    public ResponseEntity<PremioDtoResponse> savePrize(@RequestBody @Valid PremioDtoRequest premioDtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.savePrize(premioDtoRequest));
    }

    @GetMapping
    public ResponseEntity<List<PremioDtoResponse>> getAllPrize() {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.getAllPrize());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PremioDtoResponse> getOnePrize(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.getOnePrize(id));
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
