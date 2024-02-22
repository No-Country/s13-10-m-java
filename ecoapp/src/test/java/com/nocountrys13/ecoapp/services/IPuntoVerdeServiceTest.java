package com.nocountrys13.ecoapp.services;

import com.nocountrys13.ecoapp.dtos.request.CrearPuntoVerdeDtoRequest;
import com.nocountrys13.ecoapp.dtos.response.PuntoVerdeDtoResponse;
import com.nocountrys13.ecoapp.entities.PuntoVerde;
import com.nocountrys13.ecoapp.entities.Usuario;
import com.nocountrys13.ecoapp.repositories.PuntoVerdeRepository;
import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.services.impl.PuntoVerdeServiceImpl;
import com.nocountrys13.ecoapp.utils.Material;
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
class IPuntoVerdeServiceTest {


    @Mock
    private PuntoVerdeRepository puntoVerdeRepositoryMock;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @InjectMocks
    private PuntoVerdeServiceImpl puntoVerdeService;


//  @Test
//    void savePuntoVerde() {
//        UUID id = UUID.randomUUID();
//        List<Material> materiales = new ArrayList<>();
//        materiales.add(Material.CARTON);
//        materiales.add(Material.PLASTICO);
//        CrearPuntoVerdeDtoRequest crearPuntoVerdeDtoRequest = new CrearPuntoVerdeDtoRequest(id, "test",
//                "5493794249907","W3400","48541358954","81",materiales,
//                "calle falsa 123","latitud","longitud"
//                );
//        PuntoVerde puntoVerdeEntity = nuevoPuntoVerdeEntity();
//        puntoVerdeEntity.setPuntoVerdeId(id);
//
//        when(puntoVerdeRepositoryMock.save(any(PuntoVerde.class))).thenReturn(puntoVerdeEntity);
//        when(usuarioRepositoryMock.findById(id)).thenReturn(java.util.Optional.of(new Usuario()));
//
//        PuntoVerdeDtoResponse result = puntoVerdeService.savePuntoVerde(crearPuntoVerdeDtoRequest);
//
//        assertEquals(crearPuntoVerdeDtoRequest.nombre(), result.nombre());
//        assertEquals(crearPuntoVerdeDtoRequest.direccion(), resul);
//        verify(puntoVerdeRepositoryMock, times(1)).save(any(PuntoVerde.class));
//    }
//
//    @Test
//    void getPuntosVerdeByUsuarioTest(){
//        UUID id = UUID.randomUUID();
//        List<PuntoVerde> puntosVerdes = new ArrayList<>();
//        PuntoVerde puntoVerde = nuevoPuntoVerdeEntity();
//        puntoVerde.setPuntoVerdeId(id);
//        PuntoVerde puntoVerde2 = nuevoPuntoVerdeEntity();
//        puntosVerdes.add(puntoVerde);
//        puntosVerdes.add(puntoVerde2);
//
//        when(puntoVerdeRepositoryMock.findAllByUsuarioUserId(id)).thenReturn(puntosVerdes);
//
//        List<PuntoVerdeDtoResponse> result = puntoVerdeService.getPuntosVerdeByUsuario(id);
//
//        assertEquals(2, result.size());
//        assertEquals(puntosVerdes.get(0).getNombrePv(),result.get(0).nombre());
//
//    }
//
//    @Test
//     void getAllPuntosVerdeTest() {
//
//        List<PuntoVerde> puntoVerdes = new ArrayList<>();
//        var puntoVerde1 = new PuntoVerde();
//        puntoVerde1.setUsuario(new Usuario());
//        puntoVerde1.setDireccion("calle falsa 123");
//        puntoVerde1.setNombrePv("test");
//
//        var puntoVerde2 = new PuntoVerde();
//        puntoVerde2.setUsuario(new Usuario());
//        puntoVerde2.setDireccion("calle falsa 123");
//        puntoVerde2.setNombrePv("test2");
//
//        puntoVerdes.add(puntoVerde1);
//        puntoVerdes.add(puntoVerde2);
//
//        Mockito.when(puntoVerdeRepositoryMock.findAll()).thenReturn(puntoVerdes);
//
//
//        List<PuntoVerdeDtoResponse> puntoVerdeDtoList = puntoVerdeService.getAllPuntosVerde();
//
//        assertEquals(2, puntoVerdeDtoList.size());
//        assertEquals("test", puntoVerdeDtoList.get(0).nombre());
//        verify(puntoVerdeRepositoryMock, times(1)).findAll();
//
//
//
//    }
//
//
//
//    private PuntoVerdeDtoResponse nuevoPuntoVerdeDto (){
//        var materiales = new ArrayList<Material>();
//        materiales.add(Material.PAPELCARTON);
//
//        return new PuntoVerdeDtoResponse("Test","08:08-21:00","Lunes",
//                new Direccion("calle","ciudad","provincia","pais","codigo"
//                ),"3400",materiales);
//    }
//
//    private PuntoVerde nuevoPuntoVerdeEntity(){
//        var puntoVerde = new PuntoVerde();
//        var usuario = new Usuario();
//
//        puntoVerde.setDireccion(new Direccion("calle","ciudad","provincia","pais",
//                "codigo"
//        ));
//        puntoVerde.setNombrePv("test");
//        puntoVerde.setUsuario(usuario);
//        return puntoVerde;
//    }

}