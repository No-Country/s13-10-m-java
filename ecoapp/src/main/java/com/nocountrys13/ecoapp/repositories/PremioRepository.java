package com.nocountrys13.ecoapp.repositories;

import com.nocountrys13.ecoapp.entities.Premio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PremioRepository extends JpaRepository<Premio, UUID> {
    List<Premio> findAllByPuntoVerdePuntoVerdeId(UUID puntoVerdeId);
}
