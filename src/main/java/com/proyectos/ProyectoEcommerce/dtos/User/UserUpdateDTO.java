package com.proyectos.ProyectoEcommerce.dtos.User;

import lombok.Builder;

import java.util.Date;

@Builder
public record UserUpdateDTO (Long id, String firstname, String lastname,
                            String email, String cellphone,
                            Date fechaNacimiento, String username,
                            String password, Long detalleUsuarioId){
}
