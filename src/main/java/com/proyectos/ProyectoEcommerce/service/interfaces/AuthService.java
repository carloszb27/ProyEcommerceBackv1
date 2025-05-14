package com.proyectos.ProyectoEcommerce.service.interfaces;

import com.proyectos.ProyectoEcommerce.persistence.entity.Auth;
import com.proyectos.ProyectoEcommerce.persistence.entity.AuthResponse;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserCreateDTO;
import jakarta.servlet.ServletRequest;

import java.io.IOException;
import java.net.http.HttpRequest;

public interface AuthService {
    AuthResponse register(UserCreateDTO request);

    AuthResponse authenticate(Auth request) throws IOException;
}
