package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.entities.Cliente;
import com.proyectos.ProyectoEcommerce.error.exceptions.ClienteException;
import com.proyectos.ProyectoEcommerce.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoClientes(){

        List<Cliente> lista = clienteService.listarClientes();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarClientePorId(@PathVariable Long id){

        try {
            return new ResponseEntity<>(clienteService.listarClientePorId(id), HttpStatus.OK);
        } catch (ClienteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> registrarCliente(@Valid @RequestBody Cliente cliente) {

        return new ResponseEntity<>(clienteService.registrarCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id,
                                               @Valid @RequestBody Cliente cliente){

        try {
            return new ResponseEntity<>(clienteService.actualizarCliente(id, cliente), HttpStatus.OK);
        } catch (ClienteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id){

        try {
            return new ResponseEntity<>(clienteService.eliminarCliente(id), HttpStatus.OK);
        } catch (ClienteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
