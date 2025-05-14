package com.proyectos.ProyectoEcommerce.presentation.dto.Producto;

import com.proyectos.ProyectoEcommerce.persistence.enums.Categoria;
import lombok.Builder;

import java.util.Date;

@Builder
public record ProductoUpdateDTO(Long id, String nombre, String descripcion,
                                Double precio, String urlImagen, Date fechaVen,
                                Categoria categoria, Integer stock, Double descuento) {
}
