package com.proyectos.ProyectoEcommerce.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_detalle_usuario", uniqueConstraints = @UniqueConstraint(
        name = "dni_unique",
        columnNames = "dni"
))
@Getter
@Setter
@EqualsAndHashCode(exclude = {"detalleTarjeta", "direccionEnvio", "ordenVentas", "user"}, callSuper = false)
@ToString(exclude = {"detalleTarjeta", "direccionEnvio", "ordenVentas", "user"})

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleUsuario extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;
/*
    @Column(name = "nombre", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "apellidos", length = 100, nullable = false)
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email
    private String email;
*/
    @Column(name = "dni", length = 50, nullable = false, unique = true)
    @NotBlank(message = "El dni es obligatorio")
    private String dni;
/*
    @Column(name = "password", length = 100, nullable = false)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$"
            , message = "El password debe tener al menos 8 caracteres, al menos una letra en mayuscula, " +
            "una letra en minuscula, un numero y un caracter especial")
    @NotBlank(message = "El password es obligatorio")
    private String password;
*/
    /*
    @Column(name = "numTelefono", length = 50, nullable = false)
    @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
            "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
            "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$")
    @NotBlank(message = "El numero de telefono es obligatorio")
    private String numTelefono;
*/
    /*
    @Column(name = "direccion", length = 100, nullable = false)
    @NotBlank(message = "La direccion es obligatorio")
    private String direccion;
     */
/*
    @Column(name = "fechaNacimiento", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "La fecha de nacimiento es obligatorio")
    private Date fechaNacimiento;
*/

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinColumn(name = "detalle_pago")
    //@NotNull(message = "El detalle de pago es obligatorio")
    private DetalleTarjeta detalleTarjeta;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinColumn(name = "direccion_envio")
    @NotNull(message = "La direccion de envio es obligatorio")
    private DireccionEnvio direccionEnvio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "detalleUsuario", orphanRemoval = true)
    @JsonManagedReference
    private List<OrdenVenta> ordenVentas;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "usuario", nullable = false)
    private User user;

    @Column(name = "active")
    private boolean active = true;
}