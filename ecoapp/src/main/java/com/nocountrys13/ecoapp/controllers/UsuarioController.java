package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.UsuarioDto;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> saveUser(@RequestBody @Valid UsuarioDto usuarioDto) {
        return usuarioService.saveUser(usuarioDto);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getOneUser(@PathVariable(value = "id") UUID id) {
        return usuarioService.getOneUser(id);
    }


}
