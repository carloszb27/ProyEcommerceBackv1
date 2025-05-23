package com.proyectos.ProyectoEcommerce.persistence.repository;

import com.proyectos.ProyectoEcommerce.persistence.entity.OrdenVenta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenVentaRepository extends JpaRepository<OrdenVenta, Long> {

    List<OrdenVenta> findAllByActiveTrue();

    @Query("select o from OrdenVenta o where o.carrito.user.id =:idUser and o.active = true")
    List<OrdenVenta> findAllByUser(@Param("idUser") Long idUser);

    @Modifying
    @Transactional
    @Query("update OrdenVenta o set o.active=:active where o.id=:id")
    void updateOrdenVentaSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
