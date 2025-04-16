package com.proyectos.ProyectoEcommerce.error.exceptions;

public class ProductoException extends RuntimeException {
    public ProductoException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Producto con id: " + id +  " no encontrado";
    }
}
