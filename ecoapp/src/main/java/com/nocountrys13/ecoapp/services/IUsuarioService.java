package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.UsuarioDto;
import com.nocountrys13.ecoapp.entities.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService {

	
	public ResponseEntity<Usuario> saveUser(UsuarioDto usuarioDto);

	public ResponseEntity<List<Usuario>> getAllUsers();

	public ResponseEntity<Usuario> getOneUser(UUID id);

	public ResponseEntity<Usuario> updateUser(UUID id, UsuarioDto usuarioDto);

	public ResponseEntity<Object> deleteUser(UUID id);
}
