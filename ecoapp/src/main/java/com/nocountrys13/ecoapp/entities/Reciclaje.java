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
@Table(name = "reciclajes")
public class Reciclaje implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reciclajeId;
    private String tipoMateriales;
    private Integer cantidadPlastico;
    private Integer cantidadPapel;
    private Integer cantidadCarton;
    private Integer cantidadVidrio;
    private Integer cantidadMetal;
    private Integer cantidadElectronico;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "puntoverde_id")
    private PuntoVerde puntoVerde;

}
