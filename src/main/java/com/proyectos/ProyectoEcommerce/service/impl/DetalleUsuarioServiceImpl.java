package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.entities.DetalleUsuario;
import com.proyectos.ProyectoEcommerce.error.exceptions.DetalleUsuarioException;
import com.proyectos.ProyectoEcommerce.repositories.DetalleUsuarioRepository;
import com.proyectos.ProyectoEcommerce.service.DetalleUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleUsuarioServiceImpl implements DetalleUsuarioService {
    
    private DetalleUsuarioRepository detalleUsuarioRepository;

    @Autowired
    public DetalleUsuarioServiceImpl(DetalleUsuarioRepository detalleUsuarioRepository) {
        this.detalleUsuarioRepository = detalleUsuarioRepository;
    }

    @Override
    public List<DetalleUsuario> listarDetalleUsuarios() {
        List<DetalleUsuario> lista = detalleUsuarioRepository.findAll();

        return lista.size()>0 ? lista : new ArrayList<DetalleUsuario>();
    }

    @Override
    public DetalleUsuario listarDetalleUsuarioPorId(Long id) throws DetalleUsuarioException {
        DetalleUsuario usuario = detalleUsuarioRepository.findById(id)
                .orElseThrow(() -> new DetalleUsuarioException(DetalleUsuarioException.NotFoundException(id)));

        return usuario;
    }

    @Override
    public DetalleUsuario registrarDetalleUsuario(DetalleUsuario usuario) {

        return detalleUsuarioRepository.save(usuario);
    }

    @Override
    public DetalleUsuario actualizarDetalleUsuario(Long id, DetalleUsuario usuario) throws DetalleUsuarioException {

        DetalleUsuario usuarioExiste = detalleUsuarioRepository.findById(id)
                .orElseThrow(() -> new DetalleUsuarioException(DetalleUsuarioException.NotFoundException(usuario.getId())));

        return detalleUsuarioRepository.save(usuario);
    }
    
}
