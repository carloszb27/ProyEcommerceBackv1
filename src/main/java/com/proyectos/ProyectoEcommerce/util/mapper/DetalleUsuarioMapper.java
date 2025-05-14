package com.proyectos.ProyectoEcommerce.util.mapper;

import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioUpdateDTO;
import com.proyectos.ProyectoEcommerce.persistence.entity.DetalleUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(/*componentModel = "spring", uses = {OrdenVentaMapper.class}*/)
public interface DetalleUsuarioMapper {

    DetalleUsuarioMapper instancia = Mappers.getMapper(DetalleUsuarioMapper.class);

    @Mapping(target = "active", constant = "true")
    DetalleUsuario detalleUsuarioCreateDTOADetalleUsuario(DetalleUsuarioCreateDTO detalleUsuario);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "active", constant = "true")
    DetalleUsuario detalleUsuarioDTOADetalleUsuario(DetalleUsuarioDTO detalleUsuario);

    @Mapping(target = "userId", source = "user.id")
    DetalleUsuarioDTO detalleUsuarioADetalleUsuarioDTO(DetalleUsuario detalleUsuario);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "active", constant = "true")
    DetalleUsuario detalleUsuarioUpdateDTOADetalleUsuario(DetalleUsuarioUpdateDTO detalleUsuario);

    @Mapping(target = "userId", source = "user.id")
    List<DetalleUsuarioDTO> listaDetalleUsuarioAListaDetalleUsuarioDTO(List<DetalleUsuario> lista);

}
