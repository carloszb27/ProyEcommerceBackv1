package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.entities.Categoria;
import com.proyectos.ProyectoEcommerce.entities.Producto;
import com.proyectos.ProyectoEcommerce.entities.Proveedor;
import com.proyectos.ProyectoEcommerce.repositories.ProductoRepository;
import com.proyectos.ProyectoEcommerce.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductoServiceImplTest {
/*
    @Autowired
    ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {

        Producto producto = Producto.builder()
                .id(10L)
                .nombre("Lavadora")
                .descripcion("Lavadora de alta calidad")
                .precio(600)
                .cantidad(20)
                .urlImagen("acsbdbbsb")
                .fechaVen(new Date())
                .categoria(new Categoria(1L, null, true))
                .proveedor(new Proveedor(1L, null, null, null, null, true))
                .active(true)
                .build();

        Mockito.when(productoRepository.findById(10L)).thenReturn(Optional.of(producto));

    }

    @Test
    @DisplayName("Prueba de obtencion de informacion de un producto enviando un Id valido")
    public void findByIdFound() {
        Long id = 10L;

        Producto producto = productoService.listarProductoPorId(id);

        assertEquals(id, producto.getId());

        System.out.println(producto);

    }
*/
}