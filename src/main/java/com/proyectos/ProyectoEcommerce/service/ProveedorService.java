package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorDTO;
import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorUpdateDTO;

import java.util.List;

public interface ProveedorService {

    public List<ProveedorDTO> listarProveedores();
    public ProveedorDTO listarProveedorPorId(Long id);
    public ProveedorDTO registrarProveedor(ProveedorCreateDTO proveedor);
    public ProveedorDTO actualizarProveedor(ProveedorUpdateDTO proveedor);
    public String eliminarProveedor(Long id);
}
