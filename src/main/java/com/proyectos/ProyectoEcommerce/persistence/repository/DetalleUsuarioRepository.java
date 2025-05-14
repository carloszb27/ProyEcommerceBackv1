package com.proyectos.ProyectoEcommerce.persistence.repository;

import com.proyectos.ProyectoEcommerce.persistence.entity.DetalleUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleUsuarioRepository extends JpaRepository<DetalleUsuario, Long> {

    @Query("select d from DetalleUsuario d where d.user.id = :idUser and d.active = true")
    Optional<DetalleUsuario> findByUserId(Long idUser);

}
