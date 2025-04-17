package com.proyectos.ProyectoEcommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class Auth implements Serializable {

    private String email;
    private String password;
}
