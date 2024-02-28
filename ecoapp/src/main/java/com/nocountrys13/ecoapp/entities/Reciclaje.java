package com.nocountrys13.ecoapp.entities;

import com.nocountrys13.ecoapp.utils.Material;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    private List<Material> materialesRecibidos;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

}
