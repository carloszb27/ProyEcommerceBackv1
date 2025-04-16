package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public ResponseEntity<?> listarCategorias() {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll());
    }

}
