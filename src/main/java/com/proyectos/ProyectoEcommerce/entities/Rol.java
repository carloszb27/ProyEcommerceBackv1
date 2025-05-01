package com.proyectos.ProyectoEcommerce.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rol extends AuditModel implements Serializable {

    @Id
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UserRol> userRoles = new HashSet<>();


}
