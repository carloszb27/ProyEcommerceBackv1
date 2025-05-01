package com.proyectos.ProyectoEcommerce.dtos.Producto;

import com.proyectos.ProyectoEcommerce.enums.Categoria;
import lombok.Builder;

import java.util.Date;

@Builder
public record ProductoDTO(Long id, String nombre, String descripcion,
                          Double precio, String urlImagen, Date fechaVen,
                          Categoria categoria, Integer stock, Double descuento) {
}
