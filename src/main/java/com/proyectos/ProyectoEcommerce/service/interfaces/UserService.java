package com.proyectos.ProyectoEcommerce.service.interfaces;

import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserUpdateDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> listarUsers();
    public UserDTO listarUserPorId(Long id);
    public UserDTO registrarUser(UserCreateDTO user);
    public UserDTO actualizarUser(Long id, UserUpdateDTO user);
    public String eliminarUser(Long id);
}
