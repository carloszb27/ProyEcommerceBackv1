package com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario;

import com.proyectos.ProyectoEcommerce.dtos.DetalleTarjeta.DetalleTarjetaDTO;
import com.proyectos.ProyectoEcommerce.dtos.DireccionEnvio.DireccionEnvioDTO;
import lombok.Builder;

@Builder
public record DetalleUsuarioDTO(Long id, String dni, DetalleTarjetaDTO detalleTarjeta,
                                DireccionEnvioDTO direccionEnvio, Long userId) {
}
