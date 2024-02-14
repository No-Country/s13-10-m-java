package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.PuntoVerdeDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PuntoVerdeServiceTest {


    @Mock
    private PuntoVerdeRepository puntoVerdeRepositoryMock;

    @InjectMocks
    private PuntoVerdeServiceImpl puntoVerdeService;


    @Test
    void savePuntoVerde() {
        PuntoVerdeDto puntoVerdeDto = new PuntoVerdeDto("test","direccion",new Usuario());
        PuntoVerde puntoVerdeEntity = new PuntoVerde();

        when(puntoVerdeRepositoryMock.save(any())).thenReturn(puntoVerdeEntity);

        PuntoVerdeDto result = puntoVerdeService.savePuntoVerde(puntoVerdeDto);

        assertEquals(puntoVerdeDto, result);
        verify(puntoVerdeRepositoryMock, times(1)).save(any(PuntoVerde.class));
    }



    @Test
     void getAllPuntosVerde() {

        List<PuntoVerde> puntoVerdes = new ArrayList<>();
        var puntoVerde1 = new PuntoVerde();
        puntoVerde1.setUsuario(new Usuario());
        puntoVerde1.setDireccion("direccion");
        puntoVerde1.setNombrePv("test");

        var puntoVerde2 = new PuntoVerde();
        puntoVerde2.setUsuario(new Usuario());
        puntoVerde2.setDireccion("direccion");
        puntoVerde2.setNombrePv("test2");

        puntoVerdes.add(puntoVerde1);
        puntoVerdes.add(puntoVerde2);

        Mockito.when(puntoVerdeRepositoryMock.findAll()).thenReturn(puntoVerdes);

        List<PuntoVerdeDto> puntoVerdeDtos = new ArrayList<>();
        var puntoVerdeDto1 = new PuntoVerdeDto("test","direccion",new Usuario());



    }

    @Test
    void getPuntoVerdeById() {


    }

    @Test
    void deletePuntoVerde() {
    }

    private PuntoVerdeDto nuevoPuntoVerdeDto (){

        var usuario = new Usuario();
        return new PuntoVerdeDto("Test","direccion",usuario);
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