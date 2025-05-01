package com.proyectos.ProyectoEcommerce.dtos.OrdenVenta;

import com.proyectos.ProyectoEcommerce.enums.EstadoPedido;
import com.proyectos.ProyectoEcommerce.enums.MetodoPago;
import lombok.Builder;

import java.util.Date;

@Builder
public record OrdenVentaUpdateDTO(Long id, Long detalleUsuarioId, Date fechaCompra,
                                  Date fechaEntrega, Long carritoId,
                                  MetodoPago metodoPago, EstadoPedido estadoPedido) {
}
