package com.proyectos.ProyectoEcommerce.error.exceptions;

public class CarritoException extends RuntimeException {
    public CarritoException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Carrito con id: " + id +  " no encontrado";
    }
}
