package com.proyectos.ProyectoEcommerce.service.implementation;

import com.proyectos.ProyectoEcommerce.persistence.entity.*;
import com.proyectos.ProyectoEcommerce.persistence.enums.EstadoCarrito;
import com.proyectos.ProyectoEcommerce.persistence.repository.CarritoRepository;
import com.proyectos.ProyectoEcommerce.persistence.repository.ProductoRepository;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.OrdenVenta.OrdenVentaUpdateDTO;
import com.proyectos.ProyectoEcommerce.persistence.enums.EstadoPedido;
import com.proyectos.ProyectoEcommerce.service.exception.CarritoException;
import com.proyectos.ProyectoEcommerce.service.exception.OrdenVentaException;
import com.proyectos.ProyectoEcommerce.service.interfaces.DetalleUsuarioService;
import com.proyectos.ProyectoEcommerce.util.mapper.OrdenVentaMapper;
import com.proyectos.ProyectoEcommerce.persistence.repository.OrdenVentaRepository;
import com.proyectos.ProyectoEcommerce.service.interfaces.OrdenVentaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenVentaServiceImpl implements OrdenVentaService {

    private final OrdenVentaRepository ordenVentaRepository;
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private final DetalleUsuarioService detalleUsuarioService;

    @Override
    public List<OrdenVentaDTO> listarOrdenVentas() {
        List<OrdenVenta> lista = ordenVentaRepository.findAllByActiveTrue();
        return OrdenVentaMapper.instancia.listaOrdenVentaAListaOrdenVentaDTO(lista);
    }

    @Override
    public OrdenVentaDTO listarOrdenVentaPorId(Long id) throws OrdenVentaException {
        try {
            OrdenVenta ordenVenta = ordenVentaRepository.findById(id)
                    .orElseThrow(() -> new OrdenVentaException(OrdenVentaException.NotFoundException(id)));
            return OrdenVentaMapper.instancia.ordenVentaAOrdenVentaDTO(ordenVenta);
        } catch (OrdenVentaException e) {
            throw new OrdenVentaException(e.getMessage());
        }
    }

    @Override
    public OrdenVentaDTO registrarOrdenVenta(OrdenVentaCreateDTO ordenVentaCreateDTO) {
        OrdenVenta ordenVenta = OrdenVentaMapper.instancia.ordenVentaCreateDTOAOrdenVenta(ordenVentaCreateDTO);

        Carrito carrito = carritoRepository.findById(ordenVenta.getCarrito().getId())
                .orElseThrow(()-> new CarritoException(CarritoException.NotFoundException(ordenVenta.getCarrito().getId())));

        ordenVenta.setDetalleUsuario(detalleUsuarioService.listarDetallePorUserActual().get());

        actualizarStock(carrito);

        ordenVenta.setEstadoPedido(EstadoPedido.PENDIENTE);
        ordenVenta.setFechaCompra(new Date());
        ordenVenta.setFechaEntrega(this.calcularFechaEntrega());
        OrdenVenta nuevoOrdenVenta = ordenVentaRepository.save(ordenVenta);
        darDeBajaCarrito(carrito.getId());

        return OrdenVentaMapper.instancia.ordenVentaAOrdenVentaDTO(nuevoOrdenVenta);
    }

    @Override
    public OrdenVentaDTO actualizarOrdenVenta(OrdenVentaUpdateDTO ordenVentaUpdateDTO) throws OrdenVentaException {
        try {
            OrdenVenta ordenVentaExiste = ordenVentaRepository.findById(ordenVentaUpdateDTO.id())
                    .orElseThrow(() -> new OrdenVentaException(OrdenVentaException.NotFoundException(ordenVentaUpdateDTO.id())));

            OrdenVenta ordenVentaActualizado = OrdenVentaMapper.instancia.ordenVentaUpdateDTOAOrdenVenta(ordenVentaUpdateDTO);
            return OrdenVentaMapper.instancia.ordenVentaAOrdenVentaDTO(ordenVentaRepository.save(ordenVentaActualizado));

        } catch (OrdenVentaException e) {
            throw new OrdenVentaException(e.getMessage());
        }
    }

    @Override
    public String eliminarOrdenVenta(Long id) throws OrdenVentaException {
        OrdenVenta ordenVenta = ordenVentaRepository.findById(id)
                .orElseThrow(() -> new OrdenVentaException(OrdenVentaException.NotFoundException(id)));
        ordenVentaRepository.updateOrdenVentaSetActiveForId(false, id);

        return "La orden de venta se ha eliminado correctamente";
    }

    @Override
    public Date calcularFechaEntrega() {
        // ZonaId por defecto
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Un LocalDate de aqui en 15 dias
        LocalDate localDate = LocalDate.now().plusDays(15);
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    @Override
    public void darDeBajaCarrito(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId).get();
        carrito.setEstado(EstadoCarrito.PAGADO);
        carrito.setActive(false);
        carritoRepository.save(carrito);
    }

    public void actualizarStock(Carrito carrito){
        List<CarritoItem> items = carrito.getCarritoItems();

        List<Producto> productos = new ArrayList<>();

        items.forEach(item -> {

            Producto producto = item.getProducto();

            Lote lote = producto.getLote();

            lote.setStock(lote.getStock() - item.getCantidad());

            productos.add(producto);
        });

        productoRepository.saveAll(productos);
    }

}
