package com.proyectos.ProyectoEcommerce.service.exception;

public class DetalleUsuarioException extends RuntimeException {
    public DetalleUsuarioException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Detalle de usuario con id: " + id +  " no encontrado";
    }
}
