package com.proyectos.ProyectoEcommerce.persistence.repository;

import com.proyectos.ProyectoEcommerce.persistence.entity.Carrito;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    List<Carrito> findAllByActiveTrue();

    @Query("select c from Carrito c where c.user.id =:idUser and c.active = true")
    Optional<Carrito> findByUserId(@Param("idUser") Long idUser);

    boolean existsCarritoByUserId(Long id);

    @Modifying
    @Transactional
    @Query("update Carrito c set c.active =:active where c.id =:id")
    void updateCarritoSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
