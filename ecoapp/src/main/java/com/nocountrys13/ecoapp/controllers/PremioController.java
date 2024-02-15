package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.PremioDto;
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
    public ResponseEntity<Premio> savePrize(@RequestBody @Valid PremioDto premioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.savePrize(premioDto));
    }

    @GetMapping
    public ResponseEntity<List<Premio>> getAllPrize() {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.getAllPrize());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Premio> getOnePrize(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.getOnePrize(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Premio> updatePrize(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid PremioDto premioDto) {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.updatePrize(id, premioDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(premioService.deletePrize(id));
    }
}
