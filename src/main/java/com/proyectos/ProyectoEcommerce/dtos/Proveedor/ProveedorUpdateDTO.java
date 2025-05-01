package com.proyectos.ProyectoEcommerce.dtos.Proveedor;

import lombok.Builder;

@Builder
public record ProveedorUpdateDTO(Long id, String nombre, String email,
                                 String numTelefono, String direccion) {
}
