package com.proyectos.ProyectoEcommerce.dtos.Carrito;

import lombok.Builder;

import java.util.List;

@Builder
public record CarritoCreateDTO(double precio,
                               List<CarritoItemDTO> carritoItems,
                               Long userId) {
}
