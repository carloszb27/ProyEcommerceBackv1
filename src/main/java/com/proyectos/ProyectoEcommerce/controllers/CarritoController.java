package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.entities.Carrito;
import com.proyectos.ProyectoEcommerce.error.exceptions.CarritoException;
import com.proyectos.ProyectoEcommerce.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    @Autowired
    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoCarritos(){
        List<Carrito> lista = carritoService.listarCarritos();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarCarritoPorId(@PathVariable Long id){

        try {
            return new ResponseEntity<>(carritoService.listarCarritoPorId(id), HttpStatus.OK);
        } catch (CarritoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> registrarCarrito(@Valid @RequestBody Carrito carrito){
        return new ResponseEntity<>(carritoService.registrarCarrito(carrito), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarCarrito(@Valid @RequestBody Carrito carrito){

        try {
            return new ResponseEntity<>(carritoService.actualizarCarrito(carrito), HttpStatus.OK);
        } catch (CarritoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable Long id){

        try {
            return new ResponseEntity<>(carritoService.eliminarCarrito(id), HttpStatus.OK);
        } catch (CarritoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
