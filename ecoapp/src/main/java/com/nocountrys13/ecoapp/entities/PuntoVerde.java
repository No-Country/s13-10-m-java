package com.nocountrys13.ecoapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "puntos_verdes")
public class PuntoVerde implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID puntoVerdeId;
    private String nombrePv;
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

}
