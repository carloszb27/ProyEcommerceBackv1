package com.proyectos.ProyectoEcommerce.dtos.DetalleTarjeta;

import lombok.Builder;

import java.util.Date;

@Builder
public record DetalleTarjetaDTO(Long id, String numeroTarjeta) {
}
