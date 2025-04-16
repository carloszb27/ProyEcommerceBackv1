package com.proyectos.ProyectoEcommerce.repositories;

import com.proyectos.ProyectoEcommerce.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
