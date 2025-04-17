package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.entities.DetalleUsuario;

import java.util.List;

public interface DetalleUsuarioService {

    public List<DetalleUsuario> listarDetalleUsuarios();
    public DetalleUsuario listarDetalleUsuarioPorId(Long id);
    public DetalleUsuario registrarDetalleUsuario(DetalleUsuario detalleUsuario);
    public DetalleUsuario actualizarDetalleUsuario(Long id, DetalleUsuario detalleUsuario);

}
