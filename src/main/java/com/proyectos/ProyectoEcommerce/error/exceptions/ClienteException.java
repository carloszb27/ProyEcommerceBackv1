package com.proyectos.ProyectoEcommerce.error.exceptions;

public class ClienteException extends RuntimeException {
    public ClienteException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Cliente con id: " + id +  " no encontrado";
    }
}
