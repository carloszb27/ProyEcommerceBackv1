package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.dtos.User.UserDTO;
import com.proyectos.ProyectoEcommerce.entities.User;
import com.proyectos.ProyectoEcommerce.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserService2Test {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(1L)
                .firstname("Juan")
                .lastname("Perez")
                .email("juan.p@gmail.com")
                .cellphone("+51987654321")
                .fechaNacimiento(new Date())
                .username("Juan P")
                .password("Asff&gdfg753")
                .active(true)
                .build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    @DisplayName("Prueba de obtencion de informacion de un usuario enviando un Id valido")
    public void listarUserPorId() {
        Long id = 1L;
        UserDTO userDTO = userService.listarUserPorId(id);
        assertEquals(id, userDTO.id());
        System.out.println(userDTO);
    }

}
