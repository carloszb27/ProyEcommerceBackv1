package com.proyectos.ProyectoEcommerce.dtos.DetalleTarjeta;

import lombok.Builder;

import java.util.Date;

@Builder
public record DetalleTarjetaCreateDTO(String numeroTarjeta, String cvv,
                                      Date fechaVen, String password) {
}
