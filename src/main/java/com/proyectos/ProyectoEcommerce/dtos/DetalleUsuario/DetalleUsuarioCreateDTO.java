package com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario;

import com.proyectos.ProyectoEcommerce.dtos.DetalleTarjeta.DetalleTarjetaCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleTarjeta.DetalleTarjetaDTO;
import com.proyectos.ProyectoEcommerce.dtos.DireccionEnvio.DireccionEnvioCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.DireccionEnvio.DireccionEnvioDTO;
import lombok.Builder;

@Builder
public record DetalleUsuarioCreateDTO(Long id, String dni, DetalleTarjetaCreateDTO detalleTarjeta,
                                      DireccionEnvioCreateDTO direccionEnvio, Long userId) {
}
