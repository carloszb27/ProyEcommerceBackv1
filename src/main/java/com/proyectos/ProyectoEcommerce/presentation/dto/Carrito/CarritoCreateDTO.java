package com.proyectos.ProyectoEcommerce.presentation.dto.Carrito;

import lombok.Builder;

import java.util.List;

@Builder
public record CarritoCreateDTO(List<CarritoItemDTO> carritoItems) {
}
