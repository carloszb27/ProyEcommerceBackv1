package com.proyectos.ProyectoEcommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_cliente", uniqueConstraints = @UniqueConstraint(
        name = "email_unique",
        columnNames = "correo"
))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente implements Serializable {

    @Id
    @SequenceGenerator(
            name = "cliente_sequence",
            sequenceName = "cliente_sequence"
    )
    @GeneratedValue(generator = "cliente_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "apellidos", length = 100, nullable = false)
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @Column(name = "correo", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email
    private String correo;

    @Column(name = "password", length = 100, nullable = false)
    @NotBlank(message = "El password es obligatorio")
    @Size(min = 10, max = 100, message = "El password debe tener al menos 10 caracteres" +
            "y no debe exceder los 100 caracteres")
    private String password;

    @Column(name = "numTelefono", length = 50, nullable = false)
    @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
            "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
            "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$")
    @NotBlank(message = "El numero de telefono es obligatorio")
    private String numTelefono;

    @Column(name = "direccion", length = 100, nullable = false)
    @NotBlank(message = "La direccion es obligatorio")
    private String direccion;

    @Column(name = "fechaNacimiento", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "La fecha de nacimiento es obligatorio")
    private Date fechaNacimiento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", orphanRemoval = true)
    @JsonManagedReference
    private List<OrdenVenta> ordenVenta;

    @Column(name = "active")
    private boolean active = true;
}