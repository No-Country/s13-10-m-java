package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.AuthenticationDTO;
import com.nocountrys13.ecoapp.dtos.request.RegisterDtoRequest;
import com.nocountrys13.ecoapp.services.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IAuthenticationService service;

    public AuthenticationController(IAuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDtoRequest dto) {
        
        return ResponseEntity.status(201).body(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO dto) {
        return ResponseEntity.ok(service.login(dto));
    }

}
