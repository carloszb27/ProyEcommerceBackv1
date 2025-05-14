package com.proyectos.ProyectoEcommerce.service.aop;


import com.proyectos.ProyectoEcommerce.persistence.enums.RoleEnum;
import com.proyectos.ProyectoEcommerce.service.exception.UserException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
@Aspect
public class AdminOnlyAspect {

    @Before("@annotation(com.proyectos.ProyectoEcommerce.service.aop.AdminOnly)")
    public void checkIfUserIsAdmin() throws AccessDeniedException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(auth.getAuthorities());

        if (auth == null || auth.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new UserException("Solo los administradores pueden acceder a este recurso");
        }
    }
}
