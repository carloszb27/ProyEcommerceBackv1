package com.proyectos.ProyectoEcommerce.presentation.dto.DireccionEnvio;

import lombok.Builder;

@Builder
public record DireccionEnvioDTO( Long id, String direccion,
                                 String ciudad, String distrito,
                                 String codigoZip, String pais) {
}
