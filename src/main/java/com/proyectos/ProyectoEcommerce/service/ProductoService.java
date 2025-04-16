package com.proyectos.ProyectoEcommerce.service;


import com.proyectos.ProyectoEcommerce.entities.Producto;

import java.util.List;

public interface ProductoService {

    public List<Producto> listarProductos();
    public Producto listarProductoPorId(Long id);
    public List<Producto> listarProductosMasComprados();
    public Producto registrarProducto(Producto producto);
    public Producto actualizarProducto(Producto producto);
    public String eliminarProducto(Long id);
}