package com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta;

import com.proyectos.ProyectoEcommerce.persistence.enums.MetodoPago;
import lombok.Builder;

@Builder
public record OrdenVentaCreateDTO(Long carritoId, MetodoPago metodoPago) {
}
