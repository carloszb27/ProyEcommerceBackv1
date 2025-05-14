package com.proyectos.ProyectoEcommerce.persistence.repository;

import com.proyectos.ProyectoEcommerce.persistence.entity.Role;
import com.proyectos.ProyectoEcommerce.persistence.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}
