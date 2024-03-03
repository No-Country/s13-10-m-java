package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.request.UsuarioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.UsuarioDtoResponse;
import com.nocountrys13.ecoapp.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService {

    public UsuarioDtoResponse saveUser(UsuarioDtoRequest usuarioDtoRequest);

    public List<UsuarioDtoResponse> getAllUsers();

    public UsuarioDtoResponse getOneUser(UUID id);

    public Usuario findByEmail(String email);

    public UsuarioDtoResponse updateUser(UUID id, UsuarioDtoRequest usuarioDtoRequest);

    public String deleteUser(UUID id);
}
