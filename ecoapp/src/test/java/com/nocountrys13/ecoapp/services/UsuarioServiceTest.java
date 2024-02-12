package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.UsuarioDto;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        UsuarioDto usuarioDto = new UsuarioDto("Pablo", "Aquino", "pablo@email.com", "123456");
        Usuario usuario = new Usuario();
        usuario.setNombre("Pablo");
        usuario.setApellido("Aquino");
        usuario.setEmail("pablo@email.com");
        usuario.setPassword("123456");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> responseEntity = usuarioService.saveUser(usuarioDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }

    @Test
    public void testGetAllUsers() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        usuario1.setNombre("Usuario1");
        usuario2.setNombre("Usuario2");
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> responseEntity = usuarioService.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuarios, responseEntity.getBody());

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testGetOneUser() {
        UUID id = UUID.randomUUID();

        Usuario usuario = new Usuario();
        usuario.setUserId(id);
        usuario.setNombre("Usuario de prueba");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> responseEntity = usuarioService.getOneUser(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }
}