package com.proyectos.ProyectoEcommerce.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

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

    @DisplayName("Cuando se llama a user con el metodo POST y se le envia un body" +
            " con el user, devuelve el metodo entonces el estado es created")
    @Test
    void registrarUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content("{\n" +
                                "   \"firstname\":\"Luis\",\n" +
                                "   \"lastname\":\"Perez\",\n" +
                                "   \"email\":\"luis.ppp@gmail.com\",\n" +
                                "   \"cellphone\":\"+51987654321\",\n" +
                                "   \"fechaNacimiento\":\"2002-05-15\",\n" +
                                "   \"username\":\"luis.perezzz\",\n" +
                                "   \"password\":\"Asd&sf_gl789\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void actualizarUser() {
    }

    @Test
    void eliminarUser() {
    }
}