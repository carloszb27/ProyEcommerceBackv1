package com.proyectos.ProyectoEcommerce.service.exception;

public class OrdenVentaException extends RuntimeException {
    public OrdenVentaException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Orden de venta con id: " + id +  " no encontrado";
    }

}
