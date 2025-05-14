package com.proyectos.ProyectoEcommerce.presentation.controller;

import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.interfaces.DetalleUsuarioService;
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
@RequestMapping("/detalleUsuario")
@RequiredArgsConstructor
@Slf4j
@Tag(name="User detail resource")
public class DetalleUsuarioController {
    
    private final DetalleUsuarioService detalleUsuarioService;
    private final CustomResponseBuilder customResponseBuilder;

    @GetMapping("")
    public ResponseEntity<?> listadoDetalleUsuarios(){
        List<DetalleUsuarioDTO> listaDTO = detalleUsuarioService.listarDetalleUsuarios();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarDetalleUsuarioPorId(@PathVariable Long id){
        DetalleUsuarioDTO detalleUsuarioDTO = detalleUsuarioService.listarDetalleUsuarioPorId(id);
        return customResponseBuilder.crearResponse(detalleUsuarioDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarDetalleUsuario(@Valid @RequestBody DetalleUsuarioCreateDTO detalleUsuario) {
        DetalleUsuarioDTO detalleUsuarioDTO = detalleUsuarioService.registrarDetalleUsuario(detalleUsuario);
        return customResponseBuilder.crearResponse(detalleUsuarioDTO, true, detalleUsuarioDTO.id());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDetalleUsuario(@PathVariable Long id,
                                               @Valid @RequestBody DetalleUsuarioUpdateDTO detalleUsuario){
        DetalleUsuarioDTO detalleUsuarioDTO = detalleUsuarioService.actualizarDetalleUsuario(id, detalleUsuario);
        return customResponseBuilder.crearResponse(detalleUsuarioDTO);
    }
}
