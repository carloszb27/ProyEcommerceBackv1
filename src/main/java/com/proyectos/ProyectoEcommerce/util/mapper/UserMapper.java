package com.proyectos.ProyectoEcommerce.util.mapper;

import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper instancia = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "detalleUsuario.id", source = "detalleUsuarioId")
    @Mapping(target = "active", constant = "true")
    User userDTOAUser(UserDTO userDTO);

    @Mapping(target = "detalleUsuarioId", source = "detalleUsuario.id")
    UserDTO userAUserDTO(User user);

    @Mapping(target = "active", constant = "true")
    @Mapping(target = "roles", ignore = true)
    User userCreateDTOAUser(UserCreateDTO userCreateDTO);

    @Mapping(target = "active", constant = "true")
    User userUpdateDTOAUser(UserUpdateDTO userUpdateDTO);

    @Mapping(target = "detalleUsuarioId", source = "detalleUsuario.id")
    List<UserDTO> listaUserAListaUserDTO(List<User> lista);

    @Mapping(target = "detalleUsuario.id", source = "detalleUsuarioId")
    @Mapping(target = "active", constant = "true")
    List<User> listaUserDTOAListaUser(List<UserDTO> lista);


}
