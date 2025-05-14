package com.proyectos.ProyectoEcommerce.service.exception;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "User con id: " + id +  " no encontrado";
    }
}
