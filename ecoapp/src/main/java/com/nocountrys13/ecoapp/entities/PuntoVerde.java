package com.nocountrys13.ecoapp.entities;

import com.nocountrys13.ecoapp.utils.Material;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
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
    private String latitud;
    private String longitud;
    private String telefono;
    private String dni;
    private String horariosAtencion;
    private String diasAtencion;
    private String direccion;

    @Enumerated(EnumType.STRING)
    private List<Material> materialesAceptados;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "puntoVerde")
    private List<Reciclaje> listadoReciclaje;

}
