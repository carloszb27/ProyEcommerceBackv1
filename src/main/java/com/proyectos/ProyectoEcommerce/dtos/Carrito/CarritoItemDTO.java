package com.proyectos.ProyectoEcommerce.dtos.Carrito;

import lombok.Builder;

@Builder
public record CarritoItemDTO(Long productoId, double precio, int cantidad) {
}
