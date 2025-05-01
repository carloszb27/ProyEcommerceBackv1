package com.proyectos.ProyectoEcommerce.dtos.Carrito;

import lombok.Builder;

import java.util.List;

@Builder
public record CarritoUpdateDTO(Long id, double precio,
                               List<CarritoItemDTO> carritoItems,
                               Long userId) {
}
