package com.proyectos.ProyectoEcommerce.repositories;

import com.proyectos.ProyectoEcommerce.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Lote, Long> {
}
