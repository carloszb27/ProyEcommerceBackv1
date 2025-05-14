package com.proyectos.ProyectoEcommerce.presentation.controller;

import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.aop.AdminOnly;
import com.proyectos.ProyectoEcommerce.service.aop.CurrentUser;
import com.proyectos.ProyectoEcommerce.service.interfaces.UserService;
import com.proyectos.ProyectoEcommerce.presentation.Response.CustomResponseBuilder;
import com.proyectos.ProyectoEcommerce.util.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name="User resource")
public class UserController {

    private final UserService userService;
    private final CustomResponseBuilder customResponseBuilder;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary="Get all users")
    @GetMapping("")
    public ResponseEntity<?> listadoUsers(){
        log.info("GET: user {}");
        List<UserDTO> listaDTO = userService.listarUsers();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @Operation(summary="Get an user given a user id")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarUserPorId(@PathVariable Long id){
        log.info("GET: user {}", id);
        UserDTO userDTO = userService.listarUserPorId(id);
        return customResponseBuilder.crearResponse(userDTO);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary="Get the current user profile")
    @Parameter(name = "user", hidden = true)
    @GetMapping("/me")
    public ResponseEntity<?> obtenerPerfil(@CurrentUser User user){
        log.info("GET: user {}", user.getFirstname());
        return customResponseBuilder.crearResponse(UserMapper.instancia.userAUserDTO(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @AdminOnly
    @Operation(summary="Post in DB an user given a user from body")
    @PostMapping("")
    public ResponseEntity<?> registrarUser(@Valid @RequestBody UserCreateDTO user) {
        log.info("POST: user {}", user.firstname());
        UserDTO userDTO = userService.registrarUser(user);
        return customResponseBuilder.crearResponse(userDTO, true, userDTO.id());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary="Update in DB an user given a user from body")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUser(@PathVariable Long id,
                                               @Valid @RequestBody UserUpdateDTO user){
        log.info("PUT: user {}", id);
        UserDTO userDTO = userService.actualizarUser(id, user);
        return customResponseBuilder.crearResponse(userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary="Delete in DB an user given an user id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUser(@PathVariable Long id){
        log.info("DELETE: user {}", id);
        String mensaje = userService.eliminarUser(id);
        return customResponseBuilder.crearResponse(mensaje);
    }
}
