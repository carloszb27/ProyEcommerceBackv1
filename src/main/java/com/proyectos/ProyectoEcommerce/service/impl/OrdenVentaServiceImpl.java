package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaDTO;
import com.proyectos.ProyectoEcommerce.dtos.OrdenVenta.OrdenVentaUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.*;
import com.proyectos.ProyectoEcommerce.enums.EstadoPedido;
import com.proyectos.ProyectoEcommerce.error.exceptions.OrdenVentaException;
import com.proyectos.ProyectoEcommerce.mapper.OrdenVentaMapper;
import com.proyectos.ProyectoEcommerce.repositories.OrdenVentaRepository;
import com.proyectos.ProyectoEcommerce.service.OrdenVentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrdenVentaServiceImpl implements OrdenVentaService {

    private final OrdenVentaRepository ordenVentaRepository;

    @Autowired
    public OrdenVentaServiceImpl(OrdenVentaRepository ordenVentaRepository) {
        this.ordenVentaRepository = ordenVentaRepository;
    }

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
        ordenVenta.setEstadoPedido(EstadoPedido.PENDIENTE);
        ordenVenta.setFechaCompra(new Date());
        ordenVenta.setFechaEntrega(this.calcularFechaEntrega());
        OrdenVenta nuevoOrdenVenta = ordenVentaRepository.save(ordenVenta);
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

}
