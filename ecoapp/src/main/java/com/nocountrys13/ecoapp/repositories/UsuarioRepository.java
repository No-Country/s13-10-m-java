package com.nocountrys13.ecoapp.repositories;

import com.nocountrys13.ecoapp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
 
	Usuario findByEmail( String email);

	boolean existsByEmail(String email);
   

}
