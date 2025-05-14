package com.proyectos.ProyectoEcommerce.persistence.entity;

import com.proyectos.ProyectoEcommerce.persistence.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "tbl_role")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

//    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name="permission_id"))
//    private Set<PermissionEntity> permission;
}
