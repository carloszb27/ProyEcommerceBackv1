package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.entities.User;

import java.util.List;

public interface UserService {

    public List<User> listarUsers();
    public User listarUserPorId(Long id);
    public User registrarUser(User user);
    public User actualizarUser(Long id, User user);
    public String eliminarUser(Long id);
}
