package com.proyectos.ProyectoEcommerce.presentation.dto.DetalleTarjeta;

import lombok.Builder;

import java.util.Date;

@Builder
public record DetalleTarjetaUpdateDTO(Long id, String numeroTarjeta, String cvv,
                                      Date fechaVen, String password) {
}
