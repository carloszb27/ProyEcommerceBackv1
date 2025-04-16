package com.proyectos.ProyectoEcommerce.controllers;


import com.proyectos.ProyectoEcommerce.entities.OrdenVenta;
import com.proyectos.ProyectoEcommerce.error.exceptions.OrdenVentaException;
import com.proyectos.ProyectoEcommerce.service.OrdenVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenVenta")
public class OrdenVentaController {

    private OrdenVentaService ordenVentaService;

    @Autowired
    public OrdenVentaController(OrdenVentaService ordenVentaService) {
        this.ordenVentaService = ordenVentaService;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoOrdenVentas(){
        List<OrdenVenta> lista = ordenVentaService.listarOrdenVentas();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarOrdenVentaPorId(@PathVariable Long id){

        try {
            return new ResponseEntity<>(ordenVentaService.listarOrdenVentaPorId(id), HttpStatus.OK);
        } catch (OrdenVentaException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> registrarOrdenVenta(@Valid @RequestBody OrdenVenta ordenVenta){
        return new ResponseEntity<>(ordenVentaService.registrarOrdenVenta(ordenVenta), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarOrdenVenta(@Valid @RequestBody OrdenVenta ordenVenta){

        try {
            return new ResponseEntity<>(ordenVentaService.actualizarOrdenVenta(ordenVenta), HttpStatus.OK);
        } catch (OrdenVentaException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOrdenVenta(@PathVariable Long id){

        try {
            return new ResponseEntity<>(ordenVentaService.eliminarOrdenVenta(id), HttpStatus.OK);
        } catch (OrdenVentaException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoints para direccion_facturacion y direccion_envio

}
