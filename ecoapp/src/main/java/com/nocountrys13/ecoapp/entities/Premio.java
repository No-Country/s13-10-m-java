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
@Table(name = "premios")
public class Premio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID premioId;
    private String nombrePremio;
    private Integer cantidad;
    private Integer puntos;

    @ManyToOne
    @JoinColumn(name = "puntoverde_id")
    private PuntoVerde puntoVerde;

}
