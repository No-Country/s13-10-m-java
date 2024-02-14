package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.AuthenticationDTO;
import com.nocountrys13.ecoapp.dtos.RegisterDTO;
import com.nocountrys13.ecoapp.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Role;
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
   public ResponseEntity<?> register(@RequestBody RegisterDTO dto) {
      service.register(dto);
      return ResponseEntity.status(201).build();
   }

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody AuthenticationDTO dto) {
      return ResponseEntity.ok(service.login(dto));
   }

}
