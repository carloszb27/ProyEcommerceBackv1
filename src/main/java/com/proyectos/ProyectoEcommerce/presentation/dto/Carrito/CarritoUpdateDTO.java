package com.proyectos.ProyectoEcommerce.presentation.dto.Carrito;

import lombok.Builder;

import java.util.List;

@Builder
public record CarritoUpdateDTO(Long id, double precio,
                               List<CarritoItemDTO> carritoItems,
                               Long userId) {
}
