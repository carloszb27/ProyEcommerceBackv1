package com.proyectos.ProyectoEcommerce.repositories;

import com.proyectos.ProyectoEcommerce.entities.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {

}
