package com.proyectos.ProyectoEcommerce.service.interfaces;

import com.proyectos.ProyectoEcommerce.persistence.entity.DetalleUsuario;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface DetalleUsuarioService {

    public List<DetalleUsuarioDTO> listarDetalleUsuarios();
    public DetalleUsuarioDTO listarDetalleUsuarioPorId(Long id);
    public DetalleUsuarioDTO registrarDetalleUsuario(DetalleUsuarioCreateDTO detalleUsuario);
    public DetalleUsuarioDTO actualizarDetalleUsuario(Long id, DetalleUsuarioUpdateDTO detalleUsuario);
    public void validarDetalleUsuario(DetalleUsuario detalleUsuario);
    public Optional<DetalleUsuario> listarDetallePorUserActual();
}
