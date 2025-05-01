package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaDTO;
import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaUpdateDTO;

import java.util.Date;
import java.util.List;

public interface OrdenVentaService {

    public List<OrdenVentaDTO> listarOrdenVentas();
    public OrdenVentaDTO listarOrdenVentaPorId(Long id);
    public OrdenVentaDTO registrarOrdenVenta(OrdenVentaCreateDTO ordenVenta);
    public OrdenVentaDTO actualizarOrdenVenta(OrdenVentaUpdateDTO ordenVenta);
    public String eliminarOrdenVenta(Long id);
    public Date calcularFechaEntrega();
}