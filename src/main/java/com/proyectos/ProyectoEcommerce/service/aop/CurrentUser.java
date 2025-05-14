package com.proyectos.ProyectoEcommerce.service.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
public @interface CurrentUser {
}
