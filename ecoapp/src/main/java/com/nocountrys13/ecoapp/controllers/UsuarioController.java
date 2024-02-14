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
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUser(@RequestBody @Valid UsuarioDto usuarioDto) {
        return usuarioService.saveUser(usuarioDto);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getOneUser(@PathVariable(value = "id") UUID id) {
        return usuarioService.getOneUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UsuarioDto usuarioDto) {
        return usuarioService.updateUser(id, usuarioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        return usuarioService.deleteUser(id);
    }


}
