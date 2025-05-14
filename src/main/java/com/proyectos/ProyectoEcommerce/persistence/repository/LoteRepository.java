package com.proyectos.ProyectoEcommerce.persistence.repository;

import com.proyectos.ProyectoEcommerce.persistence.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

}
