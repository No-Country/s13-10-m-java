package com.nocountrys13.ecoapp.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emailVerification")
public class EmailVerification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private UUID id;
	private String token;
	private LocalDateTime creationTime;
	private LocalDateTime expirationTime;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
	private Usuario usuario;
}
