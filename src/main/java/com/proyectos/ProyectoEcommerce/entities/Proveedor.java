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
        name = "email_proveedor_unique",
        columnNames = "email"
))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proveedor extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email
    private String email;

    @Column(name = "numTelefono", length = 50, nullable = false)
    @NotBlank(message = "El numTelefono es obligatorio")
    private String numTelefono;

    @Column(name = "direccion", length = 100, nullable = false)
    @NotBlank(message = "La direccion es obligatorio")
    private String direccion;

    @Column(name = "active")
    private boolean active = true;

}
