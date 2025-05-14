package com.proyectos.ProyectoEcommerce.presentation.dto.User;

import lombok.Builder;

import java.util.Date;

@Builder
public record UserUpdateDTO (Long id, String firstname, String lastname,
                            String email, String cellphone,
                            Date fechaNacimiento,
                            String password){
}
