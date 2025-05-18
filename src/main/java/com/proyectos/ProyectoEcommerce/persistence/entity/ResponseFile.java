package com.proyectos.ProyectoEcommerce.persistence.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
}
