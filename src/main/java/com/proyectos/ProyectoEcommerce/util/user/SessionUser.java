package com.proyectos.ProyectoEcommerce.util.user;

import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.service.implementation.UserDetailImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionUser {

    public static User getUserAutenticado() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        User user = ((UserDetailImplement) authentication.getPrincipal()).getUser();

        /*
        if(authentication.getPrincipal() instanceof UserDetailImplement userDetailImplement) {
            user = userDetailImplement.getUser();
            System.out.println(user);
        }
        */

        return user;
    }
}
