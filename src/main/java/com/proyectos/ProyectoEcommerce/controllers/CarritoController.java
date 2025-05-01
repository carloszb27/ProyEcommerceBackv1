package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoDTO;
import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.CarritoService;
import com.proyectos.ProyectoEcommerce.util.CreateResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;
    private final CreateResponse createResponse;

    @Autowired
    public CarritoController(CarritoService carritoService, CreateResponse createResponse) {
        this.carritoService = carritoService;
        this.createResponse = createResponse;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoCarritos(){
        List<CarritoDTO> listaDTO = carritoService.listarCarritos();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarCarritoPorId(@PathVariable Long id){
        CarritoDTO carritoDTO = carritoService.listarCarritoPorId(id);
        return createResponse.crearResponse(carritoDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarCarrito(@Valid @RequestBody CarritoCreateDTO carrito){
        CarritoDTO carritoDTO = carritoService.registrarCarrito(carrito);
        return createResponse.crearResponse(carritoDTO, true, carritoDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarCarrito(@Valid @RequestBody CarritoUpdateDTO carrito){
        CarritoDTO carritoDTO = carritoService.actualizarCarrito(carrito);
        return createResponse.crearResponse(carritoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable Long id){
        String mensaje = carritoService.eliminarCarrito(id);
        return createResponse.crearResponse(mensaje);
    }
}
