package com.proyectos.ProyectoEcommerce.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tbl_user_rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRol extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser"/*, nullable = false*/)
    //@NotNull(message = "El user es obligatorio")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRol"/*, nullable = false*/)
    //@NotNull(message = "El rol es obligatorio")
    private Rol rol;

}
