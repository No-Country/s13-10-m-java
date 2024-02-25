package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.UsuarioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.UsuarioDtoResponse;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.IUsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDtoResponse saveUser(UsuarioDtoRequest usuarioDtoRequest) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDtoRequest, usuario);
        usuario = usuarioRepository.save(usuario);

        return new UsuarioDtoResponse(usuario.getUserId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getValidEmail(), usuario.getPuntos());

    }

    public List<UsuarioDtoResponse> getAllUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La lista de usuarios está vacía");
        }
        return usuarios.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UsuarioDtoResponse convertToDto(Usuario usuario) {
        return new UsuarioDtoResponse(usuario.getUserId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getValidEmail(), usuario.getPuntos());

    }

    public UsuarioDtoResponse getOneUser(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            var usuarioResp = usuario.get();

            return new UsuarioDtoResponse(usuarioResp.getUserId(), usuarioResp.getNombre(), usuarioResp.getApellido(), usuarioResp.getEmail(), usuarioResp.getValidEmail(), usuarioResp.getPuntos());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }

    public UsuarioDtoResponse updateUser(UUID id, UsuarioDtoRequest usuarioDtoRequest) {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
        if (usuarioBuscado.isPresent()) {
            var usuario = usuarioBuscado.get();
            BeanUtils.copyProperties(usuarioDtoRequest, usuario);
            usuario = usuarioRepository.save(usuario);
            
            return new UsuarioDtoResponse(usuario.getUserId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getValidEmail(), usuario.getPuntos());

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el usuario buscado");
    }

    public String deleteUser(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return "El usuario fue eliminado";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el usuario buscado");
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


}
