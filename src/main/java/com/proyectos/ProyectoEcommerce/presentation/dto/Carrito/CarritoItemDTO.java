package com.proyectos.ProyectoEcommerce.presentation.dto.Carrito;

import lombok.Builder;

@Builder
public record CarritoItemDTO(Long productoId, double precio, int cantidad) {
}
