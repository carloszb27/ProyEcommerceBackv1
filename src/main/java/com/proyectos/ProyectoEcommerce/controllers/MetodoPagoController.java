package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.repositories.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metodoPago")
public class MetodoPagoController {

    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    public MetodoPagoController(MetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @GetMapping
    public ResponseEntity<?> listarMetodoPagos() {
        return ResponseEntity.status(HttpStatus.OK).body(metodoPagoRepository.findAll());
    }

}
