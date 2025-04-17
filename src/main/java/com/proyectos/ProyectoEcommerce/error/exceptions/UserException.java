package com.proyectos.ProyectoEcommerce.error.exceptions;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "User con id: " + id +  " no encontrado";
    }
}
