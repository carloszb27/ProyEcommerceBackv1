package com.proyectos.ProyectoEcommerce.service.interfaces;

import com.proyectos.ProyectoEcommerce.persistence.entity.Carrito;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaUpdateDTO;

import java.util.Date;
import java.util.List;

public interface OrdenVentaService {

    public List<OrdenVentaDTO> listarOrdenVentas();
    public OrdenVentaDTO listarOrdenVentaPorId(Long id);
    public OrdenVentaDTO registrarOrdenVenta(OrdenVentaCreateDTO ordenVenta);
    public OrdenVentaDTO actualizarOrdenVenta(OrdenVentaUpdateDTO ordenVenta);
    public String eliminarOrdenVenta(Long id);
    public Date calcularFechaEntrega();
    public void darDeBajaCarrito(Long carritoId);
    public void actualizarStock(Carrito carrito);
}