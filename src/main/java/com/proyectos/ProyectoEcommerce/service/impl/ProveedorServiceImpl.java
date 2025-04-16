package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.entities.Proveedor;
import com.proyectos.ProyectoEcommerce.error.exceptions.ProveedorException;
import com.proyectos.ProyectoEcommerce.repositories.ProveedorRepository;
import com.proyectos.ProyectoEcommerce.service.ProveedorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> listarProveedores() {
        List<Proveedor> lista = proveedorRepository.findAllByActiveTrue();

        return lista.size()>0 ? lista : new ArrayList<Proveedor>();
    }

    @Override
    public Proveedor listarProveedorPorId(Long id) throws ProveedorException {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorException(ProveedorException.NotFoundException(id)));

        return proveedor;
    }

    @Override
    public Proveedor registrarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor actualizarProveedor(Proveedor proveedor) throws ProveedorException {
        Proveedor proveedorExiste = proveedorRepository.findById(proveedor.getId())
                .orElseThrow(() -> new ProveedorException(ProveedorException.NotFoundException(proveedor.getId())));

        return proveedorRepository.save(proveedor);
    }

    @Override
    public String eliminarProveedor(Long id) throws ProveedorException {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorException(ProveedorException.NotFoundException(id)));

        return "El proveedor se ha eliminado correctamente";
    }
}
