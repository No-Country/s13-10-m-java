package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.UsuarioDto;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.exceptions.UserNotFoundException;
import com.nocountrys13.ecoapp.exceptions.UsersListEmptyException;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<Usuario> saveUser(UsuarioDto usuarioDto) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (!usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }
        throw new UsersListEmptyException("La lista de usuarios está vacía");
    }

    public ResponseEntity<Usuario> getOneUser(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
        }
        throw new UserNotFoundException("Usuario no encontrado");
    }
}
