package com.proyectos.ProyectoEcommerce.presentation.controller;

import com.proyectos.ProyectoEcommerce.persistence.entity.Lote;
import com.proyectos.ProyectoEcommerce.persistence.repository.LoteRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lote")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Lote resource")
public class LoteController {

    private final LoteRepository loteRepository;

    @GetMapping("")
    public ResponseEntity<?> listadoLotes(){
        List<Lote> lista = loteRepository.findAll();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
