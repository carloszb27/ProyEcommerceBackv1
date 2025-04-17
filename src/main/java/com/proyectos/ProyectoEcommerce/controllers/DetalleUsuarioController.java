package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.entities.DetalleUsuario;
import com.proyectos.ProyectoEcommerce.error.exceptions.DetalleUsuarioException;
import com.proyectos.ProyectoEcommerce.service.DetalleUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleUsuario")
public class DetalleUsuarioController {
    
    private DetalleUsuarioService detalleUsuarioService;

    public DetalleUsuarioController(DetalleUsuarioService detalleUsuarioService) {
        this.detalleUsuarioService = detalleUsuarioService;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoDetalleUsuarios(){

        List<DetalleUsuario> lista = detalleUsuarioService.listarDetalleUsuarios();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarDetalleUsuarioPorId(@PathVariable Long id){

        try {
            return new ResponseEntity<>(detalleUsuarioService.listarDetalleUsuarioPorId(id), HttpStatus.OK);
        } catch (DetalleUsuarioException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> registrarDetalleUsuario(@Valid @RequestBody DetalleUsuario detalleUsuario) {

        return new ResponseEntity<>(detalleUsuarioService.registrarDetalleUsuario(detalleUsuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDetalleUsuario(@PathVariable Long id,
                                               @Valid @RequestBody DetalleUsuario detalleUsuario){

        try {
            return new ResponseEntity<>(detalleUsuarioService.actualizarDetalleUsuario(id, detalleUsuario), HttpStatus.OK);
        } catch (DetalleUsuarioException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    
}
