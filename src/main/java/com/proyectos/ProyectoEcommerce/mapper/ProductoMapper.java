package com.proyectos.ProyectoEcommerce.mapper;

import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoDTO;
import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper instancia = Mappers.getMapper(ProductoMapper.class);

    @Mapping(target = "lote.precio", source = "precioLote")
    @Mapping(target = "lote.stock", source = "stock")
    @Mapping(target = "lote.proveedor.id", source = "proveedorId")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "descuento", constant = "0.0")
    Producto productoCreateDtoAProducto(ProductoCreateDTO productoCreateDTO);

    @Mapping(target = "precioLote", source = "lote.precio")
    @Mapping(target = "stock", source = "lote.stock")
    @Mapping(target = "proveedorId", source = "lote.proveedor.id")
    ProductoCreateDTO productoAProductoCreateDto(Producto producto);

    @Mapping(target = "stock", source = "lote.stock")
    ProductoDTO productoAProductoDto(Producto producto);

    @Mapping(target = "lote.stock", source = "stock")
    @Mapping(target = "active", constant = "true")
    Producto productoDtoAProducto(ProductoDTO productoDTO);

    @Mapping(target = "lote.stock", source = "stock")
    @Mapping(target = "active", constant = "true")
    Producto productoUpdateDtoAProducto(ProductoUpdateDTO productoUpdateDTO);

    @Mapping(target = "stock", source = "lote.stock")
    List<ProductoDTO> listaProductoAListaProductoDto (List<Producto> listaProducto);

    @Mapping(target = "lote.stock", source = "stock")
    @Mapping(target = "active", constant = "true")
    List<Producto> listaProductoDtoAListaProducto (List<ProductoDTO> listaProductoDTO);

}
