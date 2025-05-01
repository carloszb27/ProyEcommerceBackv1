package com.proyectos.ProyectoEcommerce.mapper;

import com.proyectos.ProyectoEcommerce.dtos.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.User;
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
    User userCreateDTOAUser(UserCreateDTO userCreateDTO);

    @Mapping(target = "active", constant = "true")
    User userUpdateDTOAUser(UserUpdateDTO userUpdateDTO);

    @Mapping(target = "detalleUsuarioId", source = "detalleUsuario.id")
    List<UserDTO> listaUserAListaUserDTO(List<User> lista);

    @Mapping(target = "detalleUsuario.id", source = "detalleUsuarioId")
    @Mapping(target = "active", constant = "true")
    List<User> listaUserDTOAListaUser(List<UserDTO> lista);


}
