package com.proyectos.ProyectoEcommerce.service;


import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoDTO;
import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoUpdateDTO;
import com.proyectos.ProyectoEcommerce.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ProductoService {

    public List<ProductoDTO> listarProductos();
    public ProductoDTO listarProductoPorId(Long id);
    public List<ProductoDTO> listarProductosMasComprados();
    public ProductoDTO registrarProducto(ProductoCreateDTO producto);
    public ProductoDTO actualizarProducto(ProductoUpdateDTO producto);
    public String eliminarProducto(Long id);
    public List<ProductoDTO> actualizarProductosAPrecioOriginal();
    public Page<ProductoDTO> listarProductosPaginado(Pageable pageable);
    public List<ProductoDTO> listarProductosQueEmpiecenPor(String nombre);
    public List<ProductoDTO> listarProductosPorPrecioEntre(double p1, double p2);
    public List<ProductoDTO> listarProductosPorCantidadEntre(int c1, int c2);
    public List<ProductoDTO> listarProductosPorMuyPocaCantidad();
    public List<ProductoDTO> listarProductosQueTodaviaNoVencen();
    public List<ProductoDTO> listarProductosVencenMenosDeUnMes();
    public List<ProductoDTO> listarProductosVencidos();
    public List<ProductoDTO> listarProductosPorCategoria(Categoria categoria);
    public List<ProductoDTO> listarProductosPorProveedor(Long id);
    public ProductoDTO listarProductoPorLote(Long id);
    public String actualizarPrecioProductos(Double porcentaje);
    public Date calcularFechaProximoMes();
    public double formatearDecimal(double numero);
}