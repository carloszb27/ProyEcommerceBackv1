package com.proyectos.ProyectoEcommerce.service.interfaces;

import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Proveedor.ProveedorUpdateDTO;

import java.util.List;

public interface ProveedorService {

    public List<ProveedorDTO> listarProveedores();
    public ProveedorDTO listarProveedorPorId(Long id);
    public ProveedorDTO registrarProveedor(ProveedorCreateDTO proveedor);
    public ProveedorDTO actualizarProveedor(ProveedorUpdateDTO proveedor);
    public String eliminarProveedor(Long id);
}
