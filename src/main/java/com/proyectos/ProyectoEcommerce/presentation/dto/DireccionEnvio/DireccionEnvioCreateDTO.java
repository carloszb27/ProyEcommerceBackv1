package com.proyectos.ProyectoEcommerce.presentation.dto.DireccionEnvio;

import lombok.Builder;

@Builder
public record DireccionEnvioCreateDTO(String direccion,
                                      String ciudad, String distrito,
                                      String codigoZip, String pais) {
}
