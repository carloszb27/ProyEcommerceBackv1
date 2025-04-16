package com.proyectos.ProyectoEcommerce.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private String statusCode;
    private Object message;
    private Date date;
}
