package com.proyectos.ProyectoEcommerce.controllers;


import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaDTO;
import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.OrdenVentaService;
import com.proyectos.ProyectoEcommerce.util.CreateResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenVenta")
public class OrdenVentaController {

    private final OrdenVentaService ordenVentaService;
    private final CreateResponse createResponse;

    @Autowired
    public OrdenVentaController(OrdenVentaService ordenVentaService, CreateResponse createResponse) {
        this.ordenVentaService = ordenVentaService;
        this.createResponse = createResponse;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoOrdenVentas(){
        List<OrdenVentaDTO> listaDTO = ordenVentaService.listarOrdenVentas();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarOrdenVentaPorId(@PathVariable Long id){
        OrdenVentaDTO ordenVentaDTO = ordenVentaService.listarOrdenVentaPorId(id);
        return createResponse.crearResponse(ordenVentaDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarOrdenVenta(@Valid @RequestBody OrdenVentaCreateDTO ordenVenta){
        OrdenVentaDTO ordenVentaDTO = ordenVentaService.registrarOrdenVenta(ordenVenta);
        return createResponse.crearResponse(ordenVentaDTO, true, ordenVentaDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarOrdenVenta(@Valid @RequestBody OrdenVentaUpdateDTO ordenVenta){
        OrdenVentaDTO ordenVentaDTO = ordenVentaService.actualizarOrdenVenta(ordenVenta);
        return createResponse.crearResponse(ordenVentaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOrdenVenta(@PathVariable Long id){
        String mensaje = ordenVentaService.eliminarOrdenVenta(id);
        return createResponse.crearResponse(mensaje);
    }
}
