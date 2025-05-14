package com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario;

import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleTarjeta.DetalleTarjetaUpdateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DireccionEnvio.DireccionEnvioUpdateDTO;
import lombok.Builder;

@Builder
public record DetalleUsuarioUpdateDTO(Long id, String dni, DetalleTarjetaUpdateDTO detalleTarjeta,
                                      DireccionEnvioUpdateDTO direccionEnvio, Long userId) {
}
