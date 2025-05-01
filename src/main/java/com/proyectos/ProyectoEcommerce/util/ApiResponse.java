package com.proyectos.ProyectoEcommerce.util;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private Date fecha;
    private HttpStatus status;
    private String mensaje;
    private T contenido;
}
