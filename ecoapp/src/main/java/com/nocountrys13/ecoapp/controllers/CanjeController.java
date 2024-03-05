package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.services.ICanjeService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.nocountrys13.ecoapp.controllers.ApiConstant.ROLE_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/canjes")
@PreAuthorize(ROLE_USER)
public class CanjeController {

    private final ICanjeService service;

    @PostMapping("{premioId}")
    public ResponseEntity<?> canjearPuntos(@PathVariable UUID premioId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws MessagingException {
        service.canjearPuntos(premioId, token);
        return ResponseEntity.status(201).build();
    }
}
