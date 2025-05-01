package com.proyectos.ProyectoEcommerce.dtos.DireccionEnvio;

import lombok.Builder;

@Builder
public record DireccionEnvioUpdateDTO(Long id, String direccion,
                                      String ciudad, String distrito,
                                      String codigoZip, String pais) {
}
