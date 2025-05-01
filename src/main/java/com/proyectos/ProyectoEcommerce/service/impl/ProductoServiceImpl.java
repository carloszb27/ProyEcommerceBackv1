package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoDTO;
import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoUpdateDTO;
import com.proyectos.ProyectoEcommerce.enums.Categoria;
import com.proyectos.ProyectoEcommerce.entities.Producto;
import com.proyectos.ProyectoEcommerce.error.exceptions.ProductoException;
import com.proyectos.ProyectoEcommerce.mapper.ProductoMapper;
import com.proyectos.ProyectoEcommerce.repositories.OrdenVentaRepository;
import com.proyectos.ProyectoEcommerce.repositories.ProductoRepository;
import com.proyectos.ProyectoEcommerce.service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
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
    public List<ProductoDTO> listarProductos() {

        return ProductoMapper.instancia.
                listaProductoAListaProductoDto(
                        productoRepository.findAllByActiveTrue());


/*
        if(lista.size()>0) {

            respuesta.put("mensaje", "Lista de productos");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("contenido", lista);

            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {

            respuesta.put("mensaje", "No existen registros");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.NOT_FOUND);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
*/

    }

    @Override
    public ProductoDTO listarProductoPorId(Long id) throws ProductoException {

        try {

            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new ProductoException(
                            ProductoException.NotFoundException(id)));

            return ProductoMapper.instancia.productoAProductoDto(producto);

        } catch (ProductoException e) {

            throw new ProductoException(e.getMessage());
        }

    }

    @Override
    public List<ProductoDTO> listarProductosMasComprados() {

        List<Producto> productos = ordenVentaRepository.findAllByActiveTrue()
                .stream()
                .flatMap(ordenVenta -> ordenVenta.getCarrito().getCarritoItems()
                         .stream()).map(items ->
                        items.getProducto()).collect(Collectors.toList());

        return ProductoMapper.instancia.listaProductoAListaProductoDto(productos);

    }


    @Override
    public ProductoDTO registrarProducto(ProductoCreateDTO productoCreateDTO) {

        Producto producto = ProductoMapper.instancia.productoCreateDtoAProducto(productoCreateDTO);
        producto.setPrecio(producto.getLote().getPrecio() * 1.3);
        return ProductoMapper.instancia.productoAProductoDto(productoRepository.save(producto));

    }

    @Override
    public ProductoDTO actualizarProducto(ProductoUpdateDTO productoUpdateDTO) throws ProductoException {

        try {
            Producto productoExiste = productoRepository.findById(productoUpdateDTO.id())
                    .orElseThrow(() -> new ProductoException(ProductoException
                            .NotFoundException(productoUpdateDTO.id())));

            Producto producto = ProductoMapper.instancia.productoUpdateDtoAProducto(productoUpdateDTO);

            return ProductoMapper.instancia.productoAProductoDto(productoRepository.save(producto));

        } catch (ProductoException e) {
            throw new ProductoException(e.getMessage());
        }

    }

    @Override
    public String eliminarProducto(Long id) throws ProductoException {

        try {
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new ProductoException(ProductoException.NotFoundException(id)));
            productoRepository.updateProductoSetActiveForId(false, id);

            return "El producto se ha eliminado correctamente";
        } catch (ProductoException e) {
            throw new ProductoException(e.getMessage());
        }

    }


    @Override
    public List<ProductoDTO> actualizarProductosAPrecioOriginal() {

        List<Producto> listaProductos = new ArrayList<>();
        productoRepository.findAllByActiveTrue()
                .forEach(producto -> {

            double precioLote = producto.getLote().getPrecio();
            producto.setPrecio(precioLote * 1.3);
            listaProductos.add(producto);
        });
        productoRepository.saveAll(listaProductos);

       return ProductoMapper.instancia
               .listaProductoAListaProductoDto(productoRepository.findAllByActiveTrue());
    }

    @Override
    public Page<ProductoDTO> listarProductosPaginado(Pageable pageable) {
        Page<Producto> paginaProductos = productoRepository.findPagesAllByActiveTrue(pageable);
        return new PageImpl<>(paginaProductos.stream().map(producto ->
                ProductoMapper.instancia.productoAProductoDto(producto)).toList());
    }

    @Override
    public List<ProductoDTO> listarProductosQueEmpiecenPor(String nombre) {
        List<Producto> lista = productoRepository
                .findAllByNombreStartingWithIgnoreCaseAndActiveTrue(
                Sort.by("nombre", "precio"),nombre);
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public List<ProductoDTO> listarProductosPorPrecioEntre(double p1, double p2) {
        List<Producto> lista = productoRepository.findAllByPrecioBetweenAndActiveTrue(p1, p2);
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public List<ProductoDTO> listarProductosPorCantidadEntre(int c1, int c2) {
        List<Producto> lista = productoRepository.findAllByCantidadBetweenAndActiveTrue(c1, c2);
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public List<ProductoDTO> listarProductosPorMuyPocaCantidad() {
        List<Producto> lista = productoRepository.findAllByCantidadLessThanEqualAndActiveTrue(5);
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public List<ProductoDTO> listarProductosQueTodaviaNoVencen() {
        List<Producto> lista = productoRepository
                .findAllByFechaVenGreaterThanAndActiveTrue(new Date());
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public List<ProductoDTO> listarProductosVencenMenosDeUnMes() {

        List<Producto> lista = productoRepository
                .findAllByFechaVenProximoAVencer(this.calcularFechaProximoMes());
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public Date calcularFechaProximoMes() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Un LocalDate de aqui en un mes
        LocalDate localDate = LocalDate.now().plusMonths(1);
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    @Override
    public List<ProductoDTO> listarProductosVencidos() {
        List<Producto> lista = productoRepository
                .findAllByFechaVenLessThanEqualAndActiveTrue(new Date());
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public List<ProductoDTO> listarProductosPorCategoria(Categoria categoria) {
        List<Producto> lista = productoRepository.findAllByCategoria(categoria);
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public List<ProductoDTO> listarProductosPorProveedor(Long id) {
        List<Producto> lista = productoRepository.findAllByProveedorId(id);
        return ProductoMapper.instancia.listaProductoAListaProductoDto(lista);
    }

    @Override
    public ProductoDTO listarProductoPorLote(Long id) {
        Producto producto = productoRepository.findProductoByLoteId(id);
        return ProductoMapper.instancia.productoAProductoDto(producto);
    }

    @Override
    public String actualizarPrecioProductos(Double porcentaje) {
        productoRepository.updateAllProductosPrecios(1 + formatearDecimal(porcentaje));
        return "Se actualizo los productos en un " + porcentaje + " %";
    }

    @Override
    public double formatearDecimal(double numero) {
        BigDecimal bd = new BigDecimal(Double.toString(numero/100));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
