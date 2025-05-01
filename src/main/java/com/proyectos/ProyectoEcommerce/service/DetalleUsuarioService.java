package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioUpdateDTO;

import java.util.List;

public interface DetalleUsuarioService {

    public List<DetalleUsuarioDTO> listarDetalleUsuarios();
    public DetalleUsuarioDTO listarDetalleUsuarioPorId(Long id);
    public DetalleUsuarioDTO registrarDetalleUsuario(DetalleUsuarioCreateDTO detalleUsuario);
    public DetalleUsuarioDTO actualizarDetalleUsuario(Long id, DetalleUsuarioUpdateDTO detalleUsuario);

}
