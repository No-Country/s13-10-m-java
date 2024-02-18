package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.UsuarioDto;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IUsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService{
	
    private final UsuarioRepository usuarioRepository;
 
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
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La lista de usuarios está vacía");
    }

    public ResponseEntity<Usuario> getOneUser(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }

    public ResponseEntity<Usuario> updateUser(UUID id, UsuarioDto usuarioDto) {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
        if (usuarioBuscado.isPresent()) {
            var usuario = usuarioBuscado.get();
            BeanUtils.copyProperties(usuarioDto, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el usuario buscado");
    }

    public ResponseEntity<Object> deleteUser(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return ResponseEntity.status(HttpStatus.OK).body("El usuario fue eliminado");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el usuario buscado");
    }

	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	

}
