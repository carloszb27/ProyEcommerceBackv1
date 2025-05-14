package com.proyectos.ProyectoEcommerce.presentation.controller;

import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.interfaces.ProveedorService;
import com.proyectos.ProyectoEcommerce.presentation.Response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Supplier resource")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final CustomResponseBuilder customResponseBuilder;

    @GetMapping("")
    public ResponseEntity<?> listadoProveedores(){
        List<ProveedorDTO> listaDTO = proveedorService.listarProveedores();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarProveedorPorId(@PathVariable Long id){
        ProveedorDTO proveedorDTO = proveedorService.listarProveedorPorId(id);
        return customResponseBuilder.crearResponse(proveedorDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarProveedor(@Valid @RequestBody ProveedorCreateDTO proveedor){
        ProveedorDTO proveedorDTO = proveedorService.registrarProveedor(proveedor);
        return customResponseBuilder.crearResponse(proveedorDTO, true, proveedorDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarProveedor(@Valid @RequestBody ProveedorUpdateDTO proveedor){
        ProveedorDTO proveedorDTO = proveedorService.actualizarProveedor(proveedor);
        return customResponseBuilder.crearResponse(proveedorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id){
        String mensaje = proveedorService.eliminarProveedor(id);
        return customResponseBuilder.crearResponse(mensaje);
    }

}
