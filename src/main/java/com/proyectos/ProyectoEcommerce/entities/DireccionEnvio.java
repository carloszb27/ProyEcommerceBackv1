package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tbl_direccion_envio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DireccionEnvio implements Serializable {

    @Id
    @SequenceGenerator(
            name = "direccionenvio_sequence",
            sequenceName = "direccionenvio_sequence"
    )
    @GeneratedValue(generator = "direccionenvio_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @Column(name = "ciudad", length = 100, nullable = false)
    private String ciudad;

    @Column(name = "distrito", length = 100, nullable = false)
    private String distrito;

    @Column(name = "codigozip", length = 100, nullable = false)
    private String codigoZip;

    @Column(name = "pais", length = 100, nullable = false)
    private String pais;

    @Column(name = "active")
    private boolean active = true;
}
