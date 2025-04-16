package com.proyectos.ProyectoEcommerce.service;


import com.proyectos.ProyectoEcommerce.entities.Carrito;

import java.util.List;

public interface CarritoService {

    public List<Carrito> listarCarritos();
    public Carrito listarCarritoPorId(Long id);
    public Carrito registrarCarrito(Carrito carrito);
    public Carrito actualizarCarrito(Carrito carrito);
    public String eliminarCarrito(Long id);
}
