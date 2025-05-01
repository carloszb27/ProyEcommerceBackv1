package com.proyectos.ProyectoEcommerce.dtos.OrdenVenta;

import com.proyectos.ProyectoEcommerce.enums.MetodoPago;
import lombok.Builder;

@Builder
public record OrdenVentaCreateDTO(Long detalleUsuarioId,
                                  Long carritoId, MetodoPago metodoPago) {
}
