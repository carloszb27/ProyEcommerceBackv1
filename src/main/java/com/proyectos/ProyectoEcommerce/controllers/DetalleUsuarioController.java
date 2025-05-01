package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.DetalleUsuarioService;
import com.proyectos.ProyectoEcommerce.util.CreateResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleUsuario")
public class DetalleUsuarioController {
    
    private final DetalleUsuarioService detalleUsuarioService;
    private final CreateResponse createResponse;

    @Autowired
    public DetalleUsuarioController(DetalleUsuarioService detalleUsuarioService, CreateResponse createResponse) {
        this.detalleUsuarioService = detalleUsuarioService;
        this.createResponse = createResponse;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoDetalleUsuarios(){
        List<DetalleUsuarioDTO> listaDTO = detalleUsuarioService.listarDetalleUsuarios();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarDetalleUsuarioPorId(@PathVariable Long id){
        DetalleUsuarioDTO detalleUsuarioDTO = detalleUsuarioService.listarDetalleUsuarioPorId(id);
        return createResponse.crearResponse(detalleUsuarioDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarDetalleUsuario(@Valid @RequestBody DetalleUsuarioCreateDTO detalleUsuario) {
        DetalleUsuarioDTO detalleUsuarioDTO = detalleUsuarioService.registrarDetalleUsuario(detalleUsuario);
        return createResponse.crearResponse(detalleUsuarioDTO, true, detalleUsuarioDTO.id());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDetalleUsuario(@PathVariable Long id,
                                               @Valid @RequestBody DetalleUsuarioUpdateDTO detalleUsuario){
        DetalleUsuarioDTO detalleUsuarioDTO = detalleUsuarioService.actualizarDetalleUsuario(id, detalleUsuario);
        return createResponse.crearResponse(detalleUsuarioDTO);
    }
}
