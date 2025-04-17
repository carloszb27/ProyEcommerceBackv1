package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.entities.Carrito;
import com.proyectos.ProyectoEcommerce.entities.CarritoItem;
import com.proyectos.ProyectoEcommerce.entities.OrdenVenta;
import com.proyectos.ProyectoEcommerce.entities.Producto;
import com.proyectos.ProyectoEcommerce.error.exceptions.CarritoException;
import com.proyectos.ProyectoEcommerce.error.exceptions.OrdenVentaException;
import com.proyectos.ProyectoEcommerce.repositories.CarritoRepository;
import com.proyectos.ProyectoEcommerce.repositories.OrdenVentaRepository;
import com.proyectos.ProyectoEcommerce.repositories.ProductoRepository;
import com.proyectos.ProyectoEcommerce.service.OrdenVentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrdenVentaServiceImpl implements OrdenVentaService {

    private OrdenVentaRepository ordenVentaRepository;

    private ProductoRepository productoRepository;

    private CarritoRepository carritoRepository;

    @Autowired
    public OrdenVentaServiceImpl(OrdenVentaRepository ordenVentaRepository, ProductoRepository productoRepository, CarritoRepository carritoRepository) {
        this.ordenVentaRepository = ordenVentaRepository;
        this.productoRepository = productoRepository;
        this.carritoRepository = carritoRepository;
    }

    @Override
    public List<OrdenVenta> listarOrdenVentas() {
        List<OrdenVenta> lista = ordenVentaRepository.findAllByActiveTrue();

        return lista.size()>0 ? lista : new ArrayList<OrdenVenta>();
    }

    @Override
    public OrdenVenta listarOrdenVentaPorId(Long id) {
        OrdenVenta ordenVenta = ordenVentaRepository.findById(id)
                .orElseThrow(() -> new OrdenVentaException(OrdenVentaException.NotFoundException(id)));

        return ordenVenta;
    }

    @Override
    public OrdenVenta registrarOrdenVenta(OrdenVenta ordenVenta) {

        Carrito carrito = carritoRepository.findById(ordenVenta.getCarrito().getId())
                .orElseThrow(()-> new CarritoException(CarritoException.NotFoundException(ordenVenta.getCarrito().getId())));

        List<CarritoItem> items = carrito.getCarritoItems();

        List<Producto> productos = new ArrayList<>();

        items.forEach(item -> {

            Producto producto = item.getProducto();

            producto.getLotes().forEach(lote -> {
                lote.setStock( lote.getStock() - item.getCantidad() );
            });

            productos.add(producto);
        });

        productoRepository.saveAll(productos);


        ordenVenta.setEntregado(false);

        // ZonaId por defecto
        ZoneId defaultZoneId = ZoneId.systemDefault();

        // Un LocalDate de aqui en 15 dias
        LocalDate localDate = LocalDate.now().plusDays(15);

        Date fecha_entrega = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        ordenVenta.setFechaEntrega(fecha_entrega);

        return ordenVentaRepository.save(ordenVenta);
    }

    @Override
    public OrdenVenta actualizarOrdenVenta(OrdenVenta ordenVenta) {
        OrdenVenta ordenVentaExiste = ordenVentaRepository.findById(ordenVenta.getId())
                .orElseThrow(() -> new OrdenVentaException(OrdenVentaException.NotFoundException(ordenVenta.getId())));

        return ordenVentaRepository.save(ordenVenta);
    }

    @Override
    public String eliminarOrdenVenta(Long id) {
        OrdenVenta ordenVenta = ordenVentaRepository.findById(id)
                .orElseThrow(() -> new OrdenVentaException(OrdenVentaException.NotFoundException(id)));

        ordenVentaRepository.updateOrdenVentaSetActiveForId(false, id);

        return "La orden de venta se ha eliminado correctamente";
    }
}
