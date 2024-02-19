package com.nocountrys13.ecoapp.services.impl;

import com.nocountrys13.ecoapp.dtos.request.PremioDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PremioDtoResponse;
import com.nocountrys13.ecoapp.entities.Premio;
import com.nocountrys13.ecoapp.repositories.PremioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PremioServiceImplTest {

    @Mock
    private PremioRepository premioRepository;

    @InjectMocks
    private PremioServiceImpl premioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void savePrize() {
        PremioDtoRequest premioDtoRequest = new PremioDtoRequest("Nombre de Premio", 10, 100);

        Premio premioPersisted = new Premio();
        premioPersisted.setNombrePremio("Nombre de Premio");
        premioPersisted.setCantidad(10);
        premioPersisted.setPuntos(100);

        when(premioRepository.save(any(Premio.class))).thenReturn(premioPersisted);

        PremioDtoResponse savedPrize = premioService.savePrize(premioDtoRequest);

        // Assert
        assertEquals("Nombre de Premio", savedPrize.nombrePremio());
        assertEquals(10, savedPrize.cantidad());
        assertEquals(100, savedPrize.puntos());
    }

    @Test
    void getAllPrize() {
        List<Premio> premios = new ArrayList<>();
        Premio premio1 = new Premio(UUID.randomUUID(), "Premio 1", 100, 50, null);
        Premio premio2 = new Premio(UUID.randomUUID(), "Premio 2", 200, 75, null);
        premios.add(premio1);
        premios.add(premio2);
        when(premioRepository.findAll()).thenReturn(premios);

        // Act
        List<PremioDtoResponse> result = premioService.getAllPrize();

        // Assert
        assertEquals(premios.size(), result.size());
        assertEquals("Premio 1", result.get(0).nombrePremio());
        assertEquals(100, result.get(0).cantidad());
        assertEquals(50, result.get(0).puntos());
        assertEquals("Premio 2", result.get(1).nombrePremio());
        assertEquals(200, result.get(1).cantidad());
        assertEquals(75, result.get(1).puntos());
    }

    @Test
    void testGetAllPrizeEmptyList() {
        when(premioRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ResponseStatusException.class, () -> premioService.getAllPrize());
    }

    @Test
    void testGetOnePrize() {
        UUID id = UUID.randomUUID();
        Premio premio = new Premio(id, "Premio Test", 100, 50, null);
        when(premioRepository.findById(id)).thenReturn(Optional.of(premio));

        PremioDtoResponse result = premioService.getOnePrize(id);

        assertEquals("Premio Test", result.nombrePremio());
        assertEquals(100, result.cantidad());
        assertEquals(50, result.puntos());
    }

    @Test
    void testGetOnePrizeNotFound() {
        UUID id = UUID.randomUUID();
        when(premioRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> premioService.getOnePrize(id));
    }

    @Test
    void testDeletePrize() {
        UUID id = UUID.randomUUID();
        Premio premio = new Premio(id, "Premio Test", 100, 50, null);
        when(premioRepository.findById(id)).thenReturn(Optional.of(premio));

        String result = premioService.deletePrize(id);

        assertEquals("El premio fue eliminado", result);
        verify(premioRepository).delete(premio);
    }

    @Test
    void testDeletePrizeNotFound() {
        UUID id = UUID.randomUUID();
        when(premioRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> premioService.deletePrize(id));
    }
}