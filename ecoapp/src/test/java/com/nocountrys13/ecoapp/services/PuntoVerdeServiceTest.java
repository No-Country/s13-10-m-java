package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDto;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.services.impl.PuntoVerdeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PuntoVerdeServiceTest {


    @Mock
    private PuntoVerdeRepository puntoVerdeRepositoryMock;

    @InjectMocks
    private PuntoVerdeServiceImpl puntoVerdeService;


/*    @Test
    void savePuntoVerde() {
        PuntoVerdeDto puntoVerdeDto = new PuntoVerdeDto("test","direccion");
        PuntoVerde puntoVerdeEntity = nuevoPuntoVerdeEntity();

        when(puntoVerdeRepositoryMock.save(any())).thenReturn(puntoVerdeEntity);

        PuntoVerdeDto result = puntoVerdeService.savePuntoVerde(puntoVerdeDto);

        assertEquals(puntoVerdeDto, result);
        verify(puntoVerdeRepositoryMock, times(1)).save(any(PuntoVerde.class));
    }*/



    @Test
     void getAllPuntosVerde() {

        List<PuntoVerde> puntoVerdes = new ArrayList<>();
        var puntoVerde1 = new PuntoVerde();
        puntoVerde1.setUsuario(new Usuario());
        puntoVerde1.setDireccion("direccion");
        puntoVerde1.setNombrePv("test");

        var puntoVerde2 = new PuntoVerde();
        puntoVerde2.setUsuario(new Usuario());
        puntoVerde2.setDireccion("direccion2");
        puntoVerde2.setNombrePv("test2");

        puntoVerdes.add(puntoVerde1);
        puntoVerdes.add(puntoVerde2);

        Mockito.when(puntoVerdeRepositoryMock.findAll()).thenReturn(puntoVerdes);


        List<PuntoVerdeDto> puntoVerdeDtoList = puntoVerdeService.getAllPuntosVerde();

        assertEquals(2, puntoVerdeDtoList.size());
        assertEquals("test", puntoVerdeDtoList.get(0).nombre());
        assertEquals("direccion", puntoVerdeDtoList.get(0).direccion());
        verify(puntoVerdeRepositoryMock, times(1)).findAll();



    }

    @Test
    void getPuntoVerdeById() {
        UUID id = UUID.randomUUID();
        PuntoVerde puntoVerde = new PuntoVerde();
        puntoVerde.setPuntoVerdeId(id);
        puntoVerde.setUsuario(new Usuario());
        puntoVerde.setDireccion("direccion");
        puntoVerde.setNombrePv("test");

        Mockito.when(puntoVerdeRepositoryMock.findById(any())).thenReturn(java.util.Optional.of(puntoVerde));

        PuntoVerdeDto puntoVerdeDto = puntoVerdeService.getPuntoVerdeById(id);

        assertEquals(puntoVerdeDto.nombre(), puntoVerde.getNombrePv());
        assertEquals(puntoVerdeDto.direccion(), puntoVerde.getDireccion());
        verify(puntoVerdeRepositoryMock, times(1)).findById(any());
        verifyNoMoreInteractions(puntoVerdeRepositoryMock);

        assertNotNull(puntoVerdeDto);
        assertEquals(puntoVerdeDto.nombre(), puntoVerde.getNombrePv());

    }

    @Test
    void deletePuntoVerde() {
        UUID id = UUID.randomUUID();

        puntoVerdeService.deletePuntoVerde(id);

        verify(puntoVerdeRepositoryMock).deleteById(id);
    }

    private PuntoVerdeDto nuevoPuntoVerdeDto (){

        var usuario = new Usuario();
        return new PuntoVerdeDto("Test","direccion");
    }

    private PuntoVerde nuevoPuntoVerdeEntity(){
        var puntoVerde = new PuntoVerde();
        var usuario = new Usuario();

        puntoVerde.setDireccion("direccion");
        puntoVerde.setNombrePv("test");
        puntoVerde.setUsuario(usuario);
        return puntoVerde;
    }

}