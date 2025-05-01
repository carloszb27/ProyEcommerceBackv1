package com.proyectos.ProyectoEcommerce.repositories;

import com.proyectos.ProyectoEcommerce.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;

    User user;

    User user2;

    User user3;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .firstname("Luis")
                .lastname("Perez")
                .email("luis.perez@gmail.com")
                .cellphone("+51987654321")
                .fechaNacimiento(new Date())
                .username("luis_perez")
                .password("Dgfdjg$&_gd784")
                .active(true)
                .build();
        testEntityManager.persist(user);
        user2 = User.builder()
                .firstname("Miguel")
                .lastname("Torres")
                .email("miguel.t@gmail.com")
                .cellphone("+51987654321")
                .fechaNacimiento(new Date())
                .username("miguel_torres")
                .password("Dgfdjg$&_gd456")
                .active(true)
                .build();
        testEntityManager.persist(user2);
        user3 = User.builder()
                .firstname("Maria")
                .lastname("Ramos")
                .email("maria.r@gmail.com")
                .cellphone("+51987654321")
                .fechaNacimiento(new Date())
                .username("maria_ramos")
                .password("Dgfdjg$&_gd123")
                .active(false)
                .build();
        testEntityManager.persist(user3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllByActiveTrue() {
        List<User> lista = userRepository.findAllByActiveTrue();
        assertEquals(lista.size(), 2);
    }

    @Test
    void findOneByEmail() {
        Optional<User> usuario = userRepository.findOneByEmail("luis.perez@gmail.com");
        assertEquals(usuario.get().getEmail(), "luis.perez@gmail.com");
        assertEquals(Optional.ofNullable(null),userRepository.findOneByEmail("maria.ramos@gmail.com"));
    }

    @Test
    void findOneByUsername() {
        Optional<User> usuario = userRepository.findOneByUsername("luis_perez");
        assertEquals("luis_perez", usuario.get().getUsername());
    }

    @Test
    void saveUser() {
        User user = User.builder()
                .firstname("Luis")
                .lastname("Perez")
                .email("luis.p@gmail.com")
                .cellphone("+51987654321")
                .fechaNacimiento(new Date())
                .username("luisperez")
                .password("Asff&gdfg753")
                .active(true)
                .build();

        User nuevoUser = userRepository.save(user);
        assertEquals(user.getEmail(), nuevoUser.getEmail());
        assertNotNull(nuevoUser);
    }

    @Test
    void updateUserSetActiveForId() {
        userRepository.updateUserSetActiveForId(false,5L);
        testEntityManager.flush();
        testEntityManager.clear();
        Optional<User> user = userRepository.findById(5L);
        assertEquals(user.get().isActive(), false);
    }
}