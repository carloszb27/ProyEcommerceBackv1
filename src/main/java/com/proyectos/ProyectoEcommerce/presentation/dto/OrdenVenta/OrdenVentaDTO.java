package com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta;

import com.proyectos.ProyectoEcommerce.persistence.enums.EstadoPedido;
import com.proyectos.ProyectoEcommerce.persistence.enums.MetodoPago;
import lombok.Builder;

import java.util.Date;

@Builder
public record OrdenVentaDTO(Long id, Long detalleUsuarioId, Date fechaCompra,
                            Date fechaEntrega, Long carritoId,
                            MetodoPago metodoPago, EstadoPedido estadoPedido) {
}
