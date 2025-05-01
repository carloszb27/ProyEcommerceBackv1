package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.dtos.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.User;
import com.proyectos.ProyectoEcommerce.repositories.UserRepository;
import com.proyectos.ProyectoEcommerce.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private static List<User> lista;
    private static User obj1;
    private static User obj2;
    private static User objetoSimulado;
    private static UserDTO esperando;
    private static UserCreateDTO userCreateDTO;
    private static UserDTO nuevoUser;
    private static User user;

    private static UserUpdateDTO userUpdateDTO;
    private static User userExiste;
    private static User userActualizado;
    private static User userIncompleto;
    private static UserDTO esperado;

    @BeforeEach
    void setUp() {

    }

    @BeforeAll
    static void beforeAll() {
        lista = new ArrayList<>();
        obj1 = new User(1L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", "asdfafsd", null, null, true);
        obj2 = new User(2L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", "asdfafsd", null, null, true);
        lista.add(obj1);
        lista.add(obj2);

        objetoSimulado = new User(1L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", "asdfafsd", null, null, true);
        esperando = new UserDTO(1L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", null);

        userCreateDTO = new UserCreateDTO("Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", "asffgdfg");
        nuevoUser = new UserDTO(1L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", null);
        user = new User(null,"Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", "asffgdfg", null, null, true);

        userUpdateDTO = new UserUpdateDTO(1L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", null, null);
        userExiste = new User(1L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", "asffgdfg", null, null, true);
        userActualizado = new User(1L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", "asffgdfg", null, null, true);
        userIncompleto = new User(1L, "Luis", "", "", "", new Date(), "luisperez", "asffgdfg", null, null, true);
        esperado = new UserDTO(1L, "Luis", "Perez", "luis.p@gmail.com", "987654321", new Date(), "luisperez", null);
    }

    @Test
    void listarUsers() {
        when(userRepository.findAllByActiveTrue())
                .thenReturn(lista);

        List<UserDTO> lista = userService.listarUsers();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertEquals(2, lista.size());
        assertEquals("Luis", lista.get(0).firstname());
        assertEquals(2L, lista.get(1).id());
        verify(userRepository).findAllByActiveTrue();
    }

    @DisplayName("Dado un id cuando llamamos a 'listarUserPorId'" +
            "esperamos que retorne un usuario")
    @Test
    void listarUserPorId() {
        Long id = 1L;

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(objetoSimulado));

        UserDTO resultado = userService.listarUserPorId(id);

        assertNotNull(resultado);
        assertEquals(esperando.id(), resultado.id());
        assertEquals(esperado.email(), resultado.email());

        verify(userRepository).findById(anyLong());

    }

    @DisplayName("Dado un usuario que se quiere registrar" +
            "cuando llamamos a 'registrarUser'" +
            "esperamos que el usuario este creado")
    @Test
    void registrarUser() {

        when(userRepository.save(any(User.class)))
                .thenReturn(objetoSimulado);

        UserDTO resultado = userService.registrarUser(userCreateDTO);

        assertEquals(nuevoUser.id(), resultado.id());
        assertEquals(nuevoUser.firstname(), resultado.firstname());
        assertEquals(nuevoUser, resultado);
        verify(userRepository)
                .save(user);
    }

    @DisplayName("Dado un usuario que se quiere actualizar" +
            "cuando llamamos a 'actualizarUser'" +
            "esperamos que el usuario este actualizado")
    @Test
    void actualizarUser() {

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userActualizado));
        when(userRepository.save(userExiste)).thenReturn(userExiste);
        UserDTO actual = userService.actualizarUser(1L, userUpdateDTO);
        assertEquals(esperado.firstname(), actual.firstname());
    }

    @Test
    void eliminarUser() {
        //Given
        Long id = 1L;

        //When
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(obj1));

        String mensaje = userService.eliminarUser(id);

        //Then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(userRepository).updateUserSetActiveForId(eq(false), longArgumentCaptor.capture());

        assertEquals(1L, longArgumentCaptor.getValue());
        assertEquals("El user se ha eliminado correctamente", mensaje);
    }
}