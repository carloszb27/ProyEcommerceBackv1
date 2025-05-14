package com.proyectos.ProyectoEcommerce.util.mapper;

import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorUpdateDTO;
import com.proyectos.ProyectoEcommerce.persistence.entity.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProveedorMapper {

    ProveedorMapper instancia = Mappers.getMapper(ProveedorMapper.class);

    @Mapping(target = "active", constant = "true")
    Proveedor proveedorCreateDTOAProveedor(ProveedorCreateDTO proveedor);

    @Mapping(target = "active", constant = "true")
    Proveedor proveedorDTOAProveedor(ProveedorDTO proveedor);

    ProveedorDTO proveedorAProveedorDTO(Proveedor proveedor);

    @Mapping(target = "active", constant = "true")
    Proveedor proveedorUpdateDTOAProveedor(ProveedorUpdateDTO proveedor);

    List<ProveedorDTO> listaProveedorAListaProveedorDTO(List<Proveedor> lista);

}
