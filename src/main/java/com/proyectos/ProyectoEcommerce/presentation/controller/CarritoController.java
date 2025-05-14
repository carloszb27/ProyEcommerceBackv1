package com.proyectos.ProyectoEcommerce.presentation.controller;

import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.interfaces.CarritoService;
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
@RequestMapping("/carrito")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Carrito resource")
public class CarritoController {

    private final CarritoService carritoService;
    private final CustomResponseBuilder customResponseBuilder;

    @GetMapping("")
    public ResponseEntity<?> listadoCarritos(){
        List<CarritoDTO> listaDTO = carritoService.listarCarritos();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarCarritoPorId(@PathVariable Long id){
        CarritoDTO carritoDTO = carritoService.listarCarritoPorId(id);
        return customResponseBuilder.crearResponse(carritoDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarCarrito(@Valid @RequestBody CarritoCreateDTO carrito){
        CarritoDTO carritoDTO = carritoService.registrarCarrito(carrito);
        return customResponseBuilder.crearResponse(carritoDTO, true, carritoDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarCarrito(@Valid @RequestBody CarritoUpdateDTO carrito){
        CarritoDTO carritoDTO = carritoService.actualizarCarrito(carrito);
        return customResponseBuilder.crearResponse(carritoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable Long id){
        String mensaje = carritoService.eliminarCarrito(id);
        return customResponseBuilder.crearResponse(mensaje);
    }
}
