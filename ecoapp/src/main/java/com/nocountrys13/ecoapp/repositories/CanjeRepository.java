package com.nocountrys13.ecoapp.repositories;

import com.nocountrys13.ecoapp.entities.Canje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CanjeRepository extends JpaRepository<Canje, UUID> {
}