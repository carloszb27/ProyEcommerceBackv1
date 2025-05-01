package com.proyectos.ProyectoEcommerce.mapper;

import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaDTO;
import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.OrdenVenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface OrdenVentaMapper {

    OrdenVentaMapper instancia = Mappers.getMapper(OrdenVentaMapper.class);

    @Mapping(target = "detalleUsuario.id", source = "detalleUsuarioId")
    @Mapping(target = "carrito.id", source = "carritoId")
    @Mapping(target = "active", constant = "true")
    OrdenVenta ordenVentaCreateDTOAOrdenVenta(OrdenVentaCreateDTO ordenVenta);

    @Mapping(target = "detalleUsuario.id", source = "detalleUsuarioId")
    @Mapping(target = "carrito.id", source = "carritoId")
    @Mapping(target = "active", constant = "true")
    OrdenVenta ordenVentaDTOAOrdenVenta(OrdenVentaDTO ordenVenta);

    @Mapping(target = "detalleUsuarioId", source = "detalleUsuario.id")
    @Mapping(target = "carritoId", source = "carrito.id")
    OrdenVentaDTO ordenVentaAOrdenVentaDTO(OrdenVenta ordenVenta);

    @Mapping(target = "detalleUsuario.id", source = "detalleUsuarioId")
    @Mapping(target = "carrito.id", source = "carritoId")
    @Mapping(target = "active", constant = "true")
    OrdenVenta ordenVentaUpdateDTOAOrdenVenta(OrdenVentaUpdateDTO ordenVenta);

    @Mapping(target = "detalleUsuarioId", source = "detalleUsuario.id")
    @Mapping(target = "carritoId", source = "carrito.id")
    List<OrdenVentaDTO> listaOrdenVentaAListaOrdenVentaDTO(List<OrdenVenta> lista);
}
