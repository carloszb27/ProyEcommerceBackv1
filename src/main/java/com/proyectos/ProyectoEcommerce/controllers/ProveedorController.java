package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.entities.Proveedor;
import com.proyectos.ProyectoEcommerce.error.exceptions.ProveedorException;
import com.proyectos.ProyectoEcommerce.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }


    @GetMapping("")
    public ResponseEntity<?> listadoProveedores(){
        List<Proveedor> lista = proveedorService.listarProveedores();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarProveedorPorId(@PathVariable Long id){

        try {
            return new ResponseEntity<>(proveedorService.listarProveedorPorId(id), HttpStatus.OK);
        } catch (ProveedorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> registrarProveedor(@Valid @RequestBody Proveedor proveedor){
        return new ResponseEntity<>(proveedorService.registrarProveedor(proveedor), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarProveedor(@Valid @RequestBody Proveedor proveedor){

        try {
            return new ResponseEntity<>(proveedorService.actualizarProveedor(proveedor), HttpStatus.OK);
        } catch (ProveedorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id){

        try {
            return new ResponseEntity<>(proveedorService.eliminarProveedor(id), HttpStatus.OK);
        } catch (ProveedorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
