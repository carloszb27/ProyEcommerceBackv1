package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.entities.Proveedor;

import java.util.List;

public interface ProveedorService {

    public List<Proveedor> listarProveedores();
    public Proveedor listarProveedorPorId(Long id);
    public Proveedor registrarProveedor(Proveedor proveedor);
    public Proveedor actualizarProveedor(Proveedor proveedor);
    public String eliminarProveedor(Long id);
}
