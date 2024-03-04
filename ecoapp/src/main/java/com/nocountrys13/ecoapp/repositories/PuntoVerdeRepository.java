package com.nocountrys13.ecoapp.repositories;

import com.nocountrys13.ecoapp.entities.PuntoVerde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PuntoVerdeRepository extends JpaRepository<PuntoVerde, UUID> {

    List<PuntoVerde> findAllByUsuarioUserId(UUID userId);

    boolean existsByDireccion(String direccion);

}
