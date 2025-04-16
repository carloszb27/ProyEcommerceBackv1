package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.entities.OrdenVenta;

import java.util.List;

public interface OrdenVentaService {

    public List<OrdenVenta> listarOrdenVentas();
    public OrdenVenta listarOrdenVentaPorId(Long id);
    public OrdenVenta registrarOrdenVenta(OrdenVenta ordenVenta);
    public OrdenVenta actualizarOrdenVenta(OrdenVenta ordenVenta);
    public String eliminarOrdenVenta(Long id);
}