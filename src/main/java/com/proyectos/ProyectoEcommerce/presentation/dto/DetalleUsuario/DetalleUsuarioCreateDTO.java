package com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario;

import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleTarjeta.DetalleTarjetaCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DireccionEnvio.DireccionEnvioCreateDTO;
import lombok.Builder;

@Builder
public record DetalleUsuarioCreateDTO(String dni, DetalleTarjetaCreateDTO detalleTarjeta,
                                      DireccionEnvioCreateDTO direccionEnvio) {
}
