package com.nocountrys13.ecoapp.controllers;

import com.nocountrys13.ecoapp.dtos.request.UsuarioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.UsuarioDtoResponse;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.services.IUsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.nocountrys13.ecoapp.controllers.ApiConstant.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
@PreAuthorize(ROLE_USER)
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDtoResponse>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDtoResponse> getOneUser(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getOneUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoResponse> updateUser(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid UsuarioDtoRequest usuarioDtoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateUser(id, usuarioDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.deleteUser(id));
    }

}
