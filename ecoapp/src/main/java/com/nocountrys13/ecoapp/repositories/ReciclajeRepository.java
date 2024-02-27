package com.nocountrys13.ecoapp.repositories;

import com.nocountrys13.ecoapp.entities.Reciclaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReciclajeRepository extends JpaRepository<Reciclaje, UUID> {

}
