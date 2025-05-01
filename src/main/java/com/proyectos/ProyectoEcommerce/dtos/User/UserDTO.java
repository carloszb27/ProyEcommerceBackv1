package com.proyectos.ProyectoEcommerce.dtos.User;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Builder
public record UserDTO(Long id, String firstname, String lastname,
                      String email, String cellphone,
                      Date fechaNacimiento, String username, Long detalleUsuarioId) {
}
