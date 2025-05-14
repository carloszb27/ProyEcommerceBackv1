package com.proyectos.ProyectoEcommerce.service.exception;

public class ProductoException extends RuntimeException {
    public ProductoException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Producto con id: " + id +  " no encontrado";
    }
}
