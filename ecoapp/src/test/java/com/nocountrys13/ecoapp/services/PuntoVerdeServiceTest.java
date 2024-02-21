package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDto;
import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDto;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
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

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @InjectMocks
    private PuntoVerdeServiceImpl puntoVerdeService;


  @Test
    void savePuntoVerde() {
        UUID id = UUID.randomUUID();
        CrearPuntoVerdeDto crearPuntoVerdeDto = new CrearPuntoVerdeDto(id, "test","direccion");
        PuntoVerde puntoVerdeEntity = nuevoPuntoVerdeEntity();
        puntoVerdeEntity.setPuntoVerdeId(id);

        when(puntoVerdeRepositoryMock.save(any(PuntoVerde.class))).thenReturn(puntoVerdeEntity);
        when(usuarioRepositoryMock.findById(id)).thenReturn(java.util.Optional.of(new Usuario()));

        PuntoVerdeDto result = puntoVerdeService.savePuntoVerde(crearPuntoVerdeDto);

        assertEquals(crearPuntoVerdeDto.nombre(), result.nombre());
        assertEquals(crearPuntoVerdeDto.direccion(), result.direccion());
        verify(puntoVerdeRepositoryMock, times(1)).save(any(PuntoVerde.class));
    }


    @Test
    void getPuntosVerdeByUsuarioTest(){
        UUID id = UUID.randomUUID();
        List<PuntoVerde> puntosVerdes = new ArrayList<>();
        PuntoVerde puntoVerde = nuevoPuntoVerdeEntity();
        puntoVerde.setPuntoVerdeId(id);
        PuntoVerde puntoVerde2 = nuevoPuntoVerdeEntity();
        puntosVerdes.add(puntoVerde);
        puntosVerdes.add(puntoVerde2);

        when(puntoVerdeRepositoryMock.findAllByUsuarioUserId(id)).thenReturn(puntosVerdes);

        List<PuntoVerdeDto> result = puntoVerdeService.getPuntosVerdeByUsuario(id);

        assertEquals(2, result.size());
        assertEquals(puntosVerdes.get(0).getNombrePv(),result.get(0).nombre());


    }

    @Test
     void getAllPuntosVerdeTest() {

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
    void getPuntoVerdeByIdTest() {
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



    private PuntoVerdeDto nuevoPuntoVerdeDto (){
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