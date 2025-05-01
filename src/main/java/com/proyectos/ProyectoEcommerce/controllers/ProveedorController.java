package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorDTO;
import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.ProveedorService;
import com.proyectos.ProyectoEcommerce.util.CreateResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final CreateResponse createResponse;

    @Autowired
    public ProveedorController(ProveedorService proveedorService, CreateResponse createResponse) {
        this.proveedorService = proveedorService;
        this.createResponse = createResponse;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoProveedores(){
        List<ProveedorDTO> listaDTO = proveedorService.listarProveedores();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarProveedorPorId(@PathVariable Long id){
        ProveedorDTO proveedorDTO = proveedorService.listarProveedorPorId(id);
        return createResponse.crearResponse(proveedorDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarProveedor(@Valid @RequestBody ProveedorCreateDTO proveedor){
        ProveedorDTO proveedorDTO = proveedorService.registrarProveedor(proveedor);
        return createResponse.crearResponse(proveedorDTO, true, proveedorDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarProveedor(@Valid @RequestBody ProveedorUpdateDTO proveedor){
        ProveedorDTO proveedorDTO = proveedorService.actualizarProveedor(proveedor);
        return createResponse.crearResponse(proveedorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id){
        String mensaje = proveedorService.eliminarProveedor(id);
        return createResponse.crearResponse(mensaje);
    }

}
