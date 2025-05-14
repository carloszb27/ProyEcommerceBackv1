package com.proyectos.ProyectoEcommerce.presentation.dto.Producto;




import com.proyectos.ProyectoEcommerce.persistence.enums.Categoria;
import lombok.Builder;

import java.util.Date;

@Builder
public record ProductoCreateDTO(String nombre, String descripcion,
                                String urlImagen, Date fechaVen,
                                Categoria categoria, Double precioLote,
                                Integer stock, Long proveedorId) {
}
