package com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario;

import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleTarjeta.DetalleTarjetaDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DireccionEnvio.DireccionEnvioDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record DetalleUsuarioDTO(Long id, String dni, DetalleTarjetaDTO detalleTarjeta,
                                DireccionEnvioDTO direccionEnvio, Long userId, List<OrdenVentaDTO> ordenVentas) {
}
