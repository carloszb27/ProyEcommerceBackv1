package com.proyectos.ProyectoEcommerce.dtos.DireccionEnvio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DireccionEnvioDTO( Long id, String direccion,
                                 String ciudad, String distrito,
                                 String codigoZip, String pais) {
}
