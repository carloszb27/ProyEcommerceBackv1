package com.proyectos.ProyectoEcommerce.error.exceptions;

public class ProveedorException extends RuntimeException {
    public ProveedorException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Proveedor con id: " + id +  " no encontrado";
    }
}
