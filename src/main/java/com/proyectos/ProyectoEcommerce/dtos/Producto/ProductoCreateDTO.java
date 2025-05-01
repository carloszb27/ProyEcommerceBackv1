package com.proyectos.ProyectoEcommerce.dtos.Producto;




import com.proyectos.ProyectoEcommerce.enums.Categoria;
import lombok.Builder;

import java.util.Date;

@Builder
public record ProductoCreateDTO(String nombre, String descripcion,
                                String urlImagen, Date fechaVen,
                                Categoria categoria, Double precioLote,
                                Integer stock, Long proveedorId) {
}
