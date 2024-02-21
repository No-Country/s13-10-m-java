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
@Table(name = "canjes")
public class Canje implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID canjeId;

    @ManyToOne
    @JoinColumn(name = "premio_id")
    private Premio premio;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
