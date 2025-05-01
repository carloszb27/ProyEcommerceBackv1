package com.proyectos.ProyectoEcommerce.mapper;

import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.DetalleTarjeta;
import com.proyectos.ProyectoEcommerce.entities.DetalleUsuario;
import com.proyectos.ProyectoEcommerce.entities.DireccionEnvio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper//(componentModel = "spring", uses = {DetalleTarjeta.class, DireccionEnvio.class})
public interface DetalleUsuarioMapper {

    DetalleUsuarioMapper instancia = Mappers.getMapper(DetalleUsuarioMapper.class);

    @Mapping(target = "user.id", source = "userId")
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
