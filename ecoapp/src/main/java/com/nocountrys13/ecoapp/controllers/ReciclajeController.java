package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.request.ReciclajeDTO;
import com.nocountrys13.ecoapp.services.IReciclajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.nocountrys13.ecoapp.controllers.ApiConstant.ROLE_USER;

@RestController
@RequestMapping("/api/reciclaje")
@RequiredArgsConstructor
@PreAuthorize(ROLE_USER)
public class ReciclajeController {

    private final IReciclajeService service;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveRecicle(@Valid @RequestBody ReciclajeDTO reciclajeDTO) {
            service.save(reciclajeDTO);
            return ResponseEntity.status(201).build();
    }
}
