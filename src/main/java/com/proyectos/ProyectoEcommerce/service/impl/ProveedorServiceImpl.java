package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorDTO;
import com.proyectos.ProyectoEcommerce.dtos.Proveedor.ProveedorUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.Proveedor;
import com.proyectos.ProyectoEcommerce.error.exceptions.ProveedorException;
import com.proyectos.ProyectoEcommerce.mapper.ProveedorMapper;
import com.proyectos.ProyectoEcommerce.repositories.ProveedorRepository;
import com.proyectos.ProyectoEcommerce.service.ProveedorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ProveedorDTO> listarProveedores() {
        List<Proveedor> lista = proveedorRepository.findAllByActiveTrue();
        return ProveedorMapper.instancia.listaProveedorAListaProveedorDTO(lista);
    }

    @Override
    public ProveedorDTO listarProveedorPorId(Long id) throws ProveedorException {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorException(ProveedorException.NotFoundException(id)));
        return ProveedorMapper.instancia.proveedorAProveedorDTO(proveedor);
    }

    @Override
    public ProveedorDTO registrarProveedor(ProveedorCreateDTO proveedorCreateDTO) {
        Proveedor proveedor = ProveedorMapper.instancia.proveedorCreateDTOAProveedor(proveedorCreateDTO);
        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        return ProveedorMapper.instancia.proveedorAProveedorDTO(nuevoProveedor);
    }

    @Override
    public ProveedorDTO actualizarProveedor(ProveedorUpdateDTO proveedorUpdateDTO) throws ProveedorException {
        Proveedor proveedorExiste = proveedorRepository.findById(proveedorUpdateDTO.id())
                .orElseThrow(() -> new ProveedorException(
                        ProveedorException.NotFoundException(proveedorUpdateDTO.id())));
        Proveedor proveedor = ProveedorMapper.instancia.proveedorUpdateDTOAProveedor(proveedorUpdateDTO);
        return ProveedorMapper.instancia.proveedorAProveedorDTO(proveedorRepository.save(proveedor));
    }

    @Override
    public String eliminarProveedor(Long id) throws ProveedorException {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorException(ProveedorException.NotFoundException(id)));
        proveedorRepository.updateProveedorSetActiveForId(false, id);
        return "El proveedor se ha eliminado correctamente";
    }
}
