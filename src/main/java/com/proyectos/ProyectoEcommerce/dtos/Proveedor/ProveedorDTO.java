package com.proyectos.ProyectoEcommerce.dtos.Proveedor;

import lombok.Builder;

@Builder
public record ProveedorDTO(Long id, String nombre, String email,
                           String numTelefono, String direccion) {
}
