package com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor;

import lombok.Builder;

@Builder
public record ProveedorCreateDTO(String nombre, String email,
                                 String numTelefono, String direccion) {
}
