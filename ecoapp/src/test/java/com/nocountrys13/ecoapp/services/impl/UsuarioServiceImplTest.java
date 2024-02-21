package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.UsuarioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.UsuarioDtoResponse;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        UsuarioDtoRequest usuarioDtoRequest = new UsuarioDtoRequest("John", "Doe", "john.doe@example.com", "123456");
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDtoRequest.nombre());
        usuario.setApellido(usuarioDtoRequest.apellido());
        usuario.setEmail(usuarioDtoRequest.email());
        usuario.setPassword(usuarioDtoRequest.password());

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioDtoResponse usuarioDtoResponse = usuarioService.saveUser(usuarioDtoRequest);


        assertEquals("John", usuarioDtoResponse.nombre());
        assertEquals("Doe", usuarioDtoResponse.apellido());
        assertEquals("john.doe@example.com", usuarioDtoResponse.email());
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

        List<UsuarioDtoResponse> usuarioDtoResponses = usuarioService.getAllUsers();

        assertNotNull(usuarioDtoResponses);
        assertEquals(usuarios.size(), usuarioDtoResponses.size());
        for (int i = 0; i < usuarios.size(); i++) {
            assertEquals(usuarios.get(i).getNombre(), usuarioDtoResponses.get(i).nombre());
        }
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testGetOneUser() {
        UUID id = UUID.randomUUID();

        Usuario usuario = new Usuario();
        usuario.setUserId(id);
        usuario.setNombre("Usuario de prueba");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        UsuarioDtoResponse usuarioDtoResponse = usuarioService.getOneUser(id);

        assertNotNull(usuarioDtoResponse);
        assertEquals("Usuario de prueba", usuarioDtoResponse.nombre());
    }

    @Test
    void updateUser_UsuarioExistente_UsuarioActualizado() {
        UUID id = UUID.randomUUID();
        UsuarioDtoRequest usuarioDtoRequest = new UsuarioDtoRequest("Pablo", "Aquino", "pablo@email.com", "123456");
        Usuario usuario = new Usuario();
        usuario.setNombre("José");
        usuario.setApellido("Aquino");
        usuario.setEmail("jose@email.com");
        usuario.setPassword("67891");
        usuario.setUserId(id);
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioDtoResponse usuarioDtoResponse = usuarioService.updateUser(id, usuarioDtoRequest);

        assertNotNull(usuarioDtoResponse);

        assertEquals(usuarioDtoRequest.nombre(), usuarioDtoResponse.nombre());
        assertEquals(usuarioDtoRequest.apellido(), usuarioDtoResponse.apellido());
        assertEquals(usuarioDtoRequest.email(), usuarioDtoResponse.email());

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void updateUser_UsuarioNoExistente_ResponseStatusException() {
        UUID id = UUID.randomUUID();
        UsuarioDtoRequest usuarioDtoRequest = new UsuarioDtoRequest("Pablo", "Aquino", "pablo@email.com", "123456");

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            usuarioService.updateUser(id, usuarioDtoRequest);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("No se encontró el usuario buscado", exception.getReason());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deleteUser_UsuarioExistente_UsuarioEliminado() {
        UUID id = UUID.randomUUID();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(new Usuario()));

        String result = usuarioService.deleteUser(id);

        assertEquals("El usuario fue eliminado", result);
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).delete(any(Usuario.class));
    }

    @Test
    void deleteUser_UsuarioNoExistente_ResponseStatusException() {
        UUID id = UUID.randomUUID();
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            usuarioService.deleteUser(id);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("No se encontró el usuario buscado", exception.getReason());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, never()).delete(any());
    }
}