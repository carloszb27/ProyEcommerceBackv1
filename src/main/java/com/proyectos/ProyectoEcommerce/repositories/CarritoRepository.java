package com.proyectos.ProyectoEcommerce.repositories;

import com.proyectos.ProyectoEcommerce.entities.Carrito;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    List<Carrito> findAllByActiveTrue();

    @Query("select c from Carrito c where c.cliente.id =:idCliente and c.active = true")
    List<Carrito> findAllByCliente(@Param("idCliente") Long idCliente);

    @Modifying
    @Transactional
    @Query("update Carrito c set c.active =:active where c.id =:id")
    void updateCarritoSetActiveForId(@Param("active") boolean active, @Param("id") Long id);


}
