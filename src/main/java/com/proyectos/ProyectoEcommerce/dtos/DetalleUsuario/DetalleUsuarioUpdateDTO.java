package com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario;

import com.proyectos.ProyectoEcommerce.dtos.DetalleTarjeta.DetalleTarjetaDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleTarjeta.DetalleTarjetaUpdateDTO;
import com.proyectos.ProyectoEcommerce.dtos.DireccionEnvio.DireccionEnvioDTO;
import com.proyectos.ProyectoEcommerce.dtos.DireccionEnvio.DireccionEnvioUpdateDTO;
import lombok.Builder;

@Builder
public record DetalleUsuarioUpdateDTO(Long id, String dni, DetalleTarjetaUpdateDTO detalleTarjeta,
                                      DireccionEnvioUpdateDTO direccionEnvio, Long userId) {
}
