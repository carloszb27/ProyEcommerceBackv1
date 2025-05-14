package com.proyectos.ProyectoEcommerce.presentation.controller;


import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.interfaces.OrdenVentaService;
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
@RequestMapping("/ordenVenta")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Order resource")
public class OrdenVentaController {

    private final OrdenVentaService ordenVentaService;
    private final CustomResponseBuilder customResponseBuilder;

    @GetMapping("")
    public ResponseEntity<?> listadoOrdenVentas(){
        List<OrdenVentaDTO> listaDTO = ordenVentaService.listarOrdenVentas();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarOrdenVentaPorId(@PathVariable Long id){
        OrdenVentaDTO ordenVentaDTO = ordenVentaService.listarOrdenVentaPorId(id);
        return customResponseBuilder.crearResponse(ordenVentaDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarOrdenVenta(@Valid @RequestBody OrdenVentaCreateDTO ordenVenta){
        OrdenVentaDTO ordenVentaDTO = ordenVentaService.registrarOrdenVenta(ordenVenta);
        return customResponseBuilder.crearResponse(ordenVentaDTO, true, ordenVentaDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarOrdenVenta(@Valid @RequestBody OrdenVentaUpdateDTO ordenVenta){
        OrdenVentaDTO ordenVentaDTO = ordenVentaService.actualizarOrdenVenta(ordenVenta);
        return customResponseBuilder.crearResponse(ordenVentaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOrdenVenta(@PathVariable Long id){
        String mensaje = ordenVentaService.eliminarOrdenVenta(id);
        return customResponseBuilder.crearResponse(mensaje);
    }
}
