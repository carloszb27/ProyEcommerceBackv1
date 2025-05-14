package com.proyectos.ProyectoEcommerce.util.mapper;

import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoItemDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoUpdateDTO;
import com.proyectos.ProyectoEcommerce.persistence.entity.Carrito;
import com.proyectos.ProyectoEcommerce.persistence.entity.CarritoItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING/*, uses = {CarritoItemMapper.class}*/)
public interface CarritoMapper {

    CarritoMapper instancia = Mappers.getMapper(CarritoMapper.class);

    @Mapping(target = "active", constant = "true")
    Carrito carritoCreateDTOACarrito(CarritoCreateDTO carrito);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "active", constant = "true")
    Carrito carritoUpdateDTOACarrito(CarritoUpdateDTO carrito);

    @Mapping(target = "userId", source = "user.id")
    CarritoDTO carritoACarritoDTO(Carrito carrito);

    @Mapping(target = "userId", source = "user.id")
    List<CarritoDTO> listaCarritoAListaCarritoDTO(List<Carrito> lista);

    @Mapping(target = "producto.id", source = "productoId")
    @Mapping(target = "active", constant = "true")
    CarritoItem carritoItemDTOACarritoItem(CarritoItemDTO carritoItemDTO);

    @Mapping(target = "productoId", source = "producto.id")
    CarritoItemDTO carritoItemACarritoItemDTO(CarritoItem carritoItem);

    @Mapping(target = "producto.id", source = "productoId")
    @Mapping(target = "active", constant = "true")
    List<CarritoItem> listaCarritoItemDTOAListaCarritoItem(List<CarritoItemDTO> lista);

    @Mapping(target = "producto.id", source = "productoId")
    List<CarritoItemDTO> listaCarritoItemAListaCarritoItemDTO(List<CarritoItem> lista);

}
