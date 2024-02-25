package com.nocountrys13.ecoapp.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nocountrys13.ecoapp.entities.EmailVerification;

@Repository
public interface EmailRepository extends JpaRepository<EmailVerification, UUID> {
	
	
	@Query("SELECT ev FROM EmailVerification ev JOIN  ev.usuario u WHERE  u.userId = :usuarioId")
	Optional<EmailVerification> findByTokenAndUsuarioId( @Param("usuarioId") UUID usuarioId);
	

	
}

