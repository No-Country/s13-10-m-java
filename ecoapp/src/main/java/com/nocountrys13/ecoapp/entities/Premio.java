package com.nocountrys13.ecoapp.entities;

import com.nocountrys13.ecoapp.dtos.request.PremioDtoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
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
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "puntoverde_id")
    private PuntoVerde puntoVerde;

    public Premio(PremioDtoRequest premioDtoRequest) {
        this.nombrePremio = premioDtoRequest.nombrePremio();
        this.cantidad = premioDtoRequest.cantidad();
        this.puntos = premioDtoRequest.puntos();
    }
}
