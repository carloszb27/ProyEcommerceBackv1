package com.proyectos.ProyectoEcommerce.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tbl_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})

@Getter
@Setter
@EqualsAndHashCode(exclude = {"detalleUsuario"}, callSuper = false)
@ToString(exclude = {"detalleUsuario"})
//@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "firstname", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String firstname;

    @Column(name = "lastname", length = 100, nullable = false)
    @NotBlank(message = "Los apellidos son obligatorios")
    private String lastname;

    @Column(name = "cellphone", length = 50, nullable = false)
    @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
            "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
            "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$")
    @NotBlank(message = "El numero de telefono es obligatorio")
    private String cellphone;

    @Column(name = "fechaNacimiento", nullable = false)
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "La fecha de nacimiento es obligatorio")
    private Date fechaNacimiento;

//    @Column(name = "username", length = 50, nullable = false, unique = true)
//    @NotBlank(message = "El username es obligatorio")
//    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email
    private String email;

    @Column(name = "password"/*, nullable = false*/)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$"
            , message = "El password debe tener al menos 8 caracteres, al menos una letra en mayuscula, " +
            "una letra en minuscula, un numero y un caracter especial")
    //@NotBlank(message = "El password es obligatorio")
    private String password;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference
    private DetalleUsuario detalleUsuario;

    @Column(name = "active")
    private boolean active = true;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "tbl_user_roles", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

}
