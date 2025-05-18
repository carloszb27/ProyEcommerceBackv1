package com.proyectos.ProyectoEcommerce.persistence.repository;

import com.proyectos.ProyectoEcommerce.persistence.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {
}
