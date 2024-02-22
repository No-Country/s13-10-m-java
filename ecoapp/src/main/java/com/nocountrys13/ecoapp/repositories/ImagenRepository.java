package com.nocountrys13.ecoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nocountrys13.ecoapp.entities.Imagen;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

}
