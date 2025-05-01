package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class DireccionEnvio extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "direccion", length = 100, nullable = false)
    @NotBlank(message = "La direccion es obligatorio")
    private String direccion;

    @Column(name = "ciudad", length = 100, nullable = false)
    @NotBlank(message = "La ciudad es obligatorio")
    private String ciudad;

    @Column(name = "distrito", length = 100, nullable = false)
    @NotBlank(message = "El distrito es obligatorio")
    private String distrito;

    @Column(name = "codigozip", length = 100, nullable = false)
    @NotBlank(message = "El codigoZip es obligatorio")
    private String codigoZip;

    @Column(name = "pais", length = 100, nullable = false)
    @NotBlank(message = "El pais es obligatorio")
    private String pais;

    @Column(name = "active")
    private boolean active = true;
}
