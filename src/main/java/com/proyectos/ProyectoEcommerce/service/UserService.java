package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.dtos.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserUpdateDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> listarUsers();
    public UserDTO listarUserPorId(Long id);
    public UserDTO registrarUser(UserCreateDTO user);
    public UserDTO actualizarUser(Long id, UserUpdateDTO user);
    public String eliminarUser(Long id);
}
