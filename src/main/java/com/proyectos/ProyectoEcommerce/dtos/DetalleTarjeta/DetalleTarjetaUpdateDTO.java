package com.proyectos.ProyectoEcommerce.dtos.DetalleTarjeta;

import lombok.Builder;

import java.util.Date;

@Builder
public record DetalleTarjetaUpdateDTO(Long id, String numeroTarjeta, String cvv,
                                      Date fechaVen, String password) {
}
