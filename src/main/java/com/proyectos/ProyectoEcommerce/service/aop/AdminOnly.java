package com.proyectos.ProyectoEcommerce.service.aop;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface AdminOnly {
}
