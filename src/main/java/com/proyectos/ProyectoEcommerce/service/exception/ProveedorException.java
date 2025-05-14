package com.proyectos.ProyectoEcommerce.service.exception;

public class ProveedorException extends RuntimeException {
    public ProveedorException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Proveedor con id: " + id +  " no encontrado";
    }
}
