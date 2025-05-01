package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.entities.Lote;
import com.proyectos.ProyectoEcommerce.repositories.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lote")
public class LoteController {

    private final LoteRepository loteRepository;

    @Autowired
    public LoteController(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoLotes(){
        List<Lote> lista = loteRepository.findAll();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
