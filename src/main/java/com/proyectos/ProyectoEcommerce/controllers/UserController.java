package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.dtos.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.UserService;
import com.proyectos.ProyectoEcommerce.util.CreateResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CreateResponse createResponse;

    @Autowired
    public UserController(UserService userService, CreateResponse createResponse) {
        this.userService = userService;
        this.createResponse = createResponse;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoUsers(){
        List<UserDTO> listaDTO = userService.listarUsers();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUserPorId(@PathVariable Long id){
        UserDTO userDTO = userService.listarUserPorId(id);
        return createResponse.crearResponse(userDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarUser(@Valid @RequestBody UserCreateDTO user) {
        UserDTO userDTO = userService.registrarUser(user);
        return createResponse.crearResponse(userDTO, true, userDTO.id());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUser(@PathVariable Long id,
                                               @Valid @RequestBody UserUpdateDTO user){
        UserDTO userDTO = userService.actualizarUser(id, user);
        return createResponse.crearResponse(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUser(@PathVariable Long id){
        String mensaje = userService.eliminarUser(id);
        return createResponse.crearResponse(mensaje);
    }
}
