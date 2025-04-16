package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "categoria", uniqueConstraints = @UniqueConstraint(
        name = "nombre_unique",
        columnNames = "nombre"
))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria implements Serializable {

    @Id
    @SequenceGenerator(
            name = "categoria_sequence",
            sequenceName = "categoria_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "categoria_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(name = "active", nullable = false)
    private boolean active = true;
}
