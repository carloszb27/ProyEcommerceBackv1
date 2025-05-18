package com.proyectos.ProyectoEcommerce.presentation.controller;

import com.proyectos.ProyectoEcommerce.presentation.Response.CustomResponseBuilder;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.interfaces.ProveedorService;
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

    @GetMapping("")
    public ResponseEntity<?> listadoProveedores(){
        List<ProveedorDTO> listaDTO = proveedorService.listarProveedores();
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarProveedorPorId(@PathVariable Long id){
        ProveedorDTO proveedorDTO = proveedorService.listarProveedorPorId(id);
        return CustomResponseBuilder.getInstance().crearResponse(proveedorDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarProveedor(@Valid @RequestBody ProveedorCreateDTO proveedor){
        ProveedorDTO proveedorDTO = proveedorService.registrarProveedor(proveedor);
        return CustomResponseBuilder.getInstance().crearResponse(proveedorDTO, true, proveedorDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarProveedor(@Valid @RequestBody ProveedorUpdateDTO proveedor){
        ProveedorDTO proveedorDTO = proveedorService.actualizarProveedor(proveedor);
        return CustomResponseBuilder.getInstance().crearResponse(proveedorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id){
        String mensaje = proveedorService.eliminarProveedor(id);
        return CustomResponseBuilder.getInstance().crearResponse(mensaje);
    }

}
