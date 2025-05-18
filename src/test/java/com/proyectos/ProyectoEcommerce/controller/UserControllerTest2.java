package com.proyectos.ProyectoEcommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserCreateDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class UserControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Cuando se llama a los users entonces el estado es 200")
    @Test
    void listadoUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void listarUserPorId() {
    }
/*
    @DisplayName("Cuando se llama a user con el metodo POST y se le envia un body" +
            " con el user, devuelve el metodo entonces el estado es created")
    @Test
    void registrarUser() throws Exception {

        UserCreateDTO userCreateDTO = UserCreateDTO
                .builder()
                        .firstname("Luis")
                                .lastname("Perez")
                                        .email("luis.ppp@gmail.com")
                                                .cellphone("+51987654321")
                                                        .fechaNacimiento(new Date(2002,05,15))
                                                                .password("Asd&sf_gl789")
                                                                        .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userCreateDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith
                                (MediaType.APPLICATION_JSON));
    }
*/
    public static String asJsonString(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Test
    void actualizarUser() {
    }

    @Test
    void eliminarUser() {
    }
}