package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_proveedor", uniqueConstraints = @UniqueConstraint(
        name = "emailproveedor_unique",
        columnNames = "correo"
))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proveedor implements Serializable {

    @Id
    @SequenceGenerator(
            name = "proveedor_sequence",
            sequenceName = "proveedor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "proveedor_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "correo", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email
    private String correo;

    @Column(name = "numTelefono", length = 50, nullable = false)
    private String numTelefono;

    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @Column(name = "active")
    private boolean active = true;

}
