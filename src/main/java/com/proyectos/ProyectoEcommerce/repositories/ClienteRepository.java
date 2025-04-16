package com.proyectos.ProyectoEcommerce.repositories;

import com.proyectos.ProyectoEcommerce.entities.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("update Cliente c set c.active =:active where c.id =:id")
    void updateClienteSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
