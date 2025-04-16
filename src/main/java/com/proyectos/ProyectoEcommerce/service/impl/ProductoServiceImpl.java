package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.entities.Producto;
import com.proyectos.ProyectoEcommerce.error.exceptions.ProductoException;
import com.proyectos.ProyectoEcommerce.repositories.OrdenVentaRepository;
import com.proyectos.ProyectoEcommerce.repositories.ProductoRepository;
import com.proyectos.ProyectoEcommerce.service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    private final OrdenVentaRepository ordenVentaRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, OrdenVentaRepository ordenVentaRepository) {
        this.productoRepository = productoRepository;
        this.ordenVentaRepository = ordenVentaRepository;
    }

    @Override
    public List<Producto> listarProductos() {
        List<Producto> lista = productoRepository.findAllByActiveTrue();

        return lista.size()>0 ? lista : new ArrayList<Producto>();
    }

    @Override
    public Producto listarProductoPorId(Long id) throws ProductoException {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException(ProductoException.NotFoundException(id)));

        return producto;
    }

    @Override
    public List<Producto> listarProductosMasComprados() {

        // Primero

        /*public List<Producto> listarProductosMasComprados() {

    List<Producto> productos = ordenVentaRepository.findAllByActiveTrue()
            .stream()
            .map(ordenVenta -> ordenVenta.getCarrito().getCarritoItems()
                     .stream()
                     .map(items -> items.getProducto())
                     .toList())
            .toList();

    return productos;*/


        List<Producto> productos = ordenVentaRepository.findAllByActiveTrue()
                .stream()
                .flatMap(ordenVenta -> ordenVenta.getCarrito().getCarritoItems()
                         .stream()).map(items -> items.getProducto()).collect(Collectors.toList());


        // con metodos de referencia

        /*return ordenVentaRepository.findAllByActiveTrue()
            .stream()
            .flatMap(ordenVenta -> ordenVenta.getCarrito().getCarritoItems().stream())
            .map(CarritoItem::getProducto)
            .collect(Collectors.toList());*/

        // si devuelve los productos mas comprados

        /*return ordenVentaRepository.findAllByActiveTrue()
            .stream()
            .flatMap(orden -> orden.getCarrito().getCarritoItems().stream())
            .collect(Collectors.groupingBy(
                CarritoItem::getProducto,
                Collectors.summingInt(CarritoItem::getCantidad)
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<Producto, Integer>comparingByValue().reversed())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());*/


        return productos;
    }

    @Override
    public Producto registrarProducto(Producto producto) {

        producto.getStock().forEach(stock -> {
            producto.setPrecio(stock.getPrecio()*1.2);
        });

        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) throws ProductoException {
        Producto productoExiste = productoRepository.findById(producto.getId())
                .orElseThrow(() -> new ProductoException(ProductoException.NotFoundException(producto.getId())));

        return productoRepository.save(producto);
    }

    @Override
    public String eliminarProducto(Long id) throws ProductoException {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException(ProductoException.NotFoundException(id)));

        return "El producto se ha eliminado correctamente";
    }
}
