package com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor;

import lombok.Builder;

@Builder
public record ProveedorUpdateDTO(Long id, String nombre, String email,
                                 String numTelefono, String direccion) {
}
