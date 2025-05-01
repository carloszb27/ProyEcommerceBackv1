package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioDTO;
import com.proyectos.ProyectoEcommerce.dtos.DetalleUsuario.DetalleUsuarioUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.DetalleUsuario;
import com.proyectos.ProyectoEcommerce.error.exceptions.DetalleUsuarioException;
import com.proyectos.ProyectoEcommerce.mapper.DetalleUsuarioMapper;
import com.proyectos.ProyectoEcommerce.repositories.DetalleUsuarioRepository;
import com.proyectos.ProyectoEcommerce.service.DetalleUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DetalleUsuarioServiceImpl implements DetalleUsuarioService {
    
    private final DetalleUsuarioRepository detalleUsuarioRepository;

    @Autowired
    public DetalleUsuarioServiceImpl(DetalleUsuarioRepository detalleUsuarioRepository) {
        this.detalleUsuarioRepository = detalleUsuarioRepository;
    }

    @Override
    public List<DetalleUsuarioDTO> listarDetalleUsuarios() {
        List<DetalleUsuario> lista = detalleUsuarioRepository.findAll();
        return DetalleUsuarioMapper.instancia.listaDetalleUsuarioAListaDetalleUsuarioDTO(lista);
    }

    @Override
    public DetalleUsuarioDTO listarDetalleUsuarioPorId(Long id) throws DetalleUsuarioException {
        try {
            DetalleUsuario detalleUsuario = detalleUsuarioRepository.findById(id)
                    .orElseThrow(() -> new DetalleUsuarioException(DetalleUsuarioException.NotFoundException(id)));

            return DetalleUsuarioMapper.instancia.detalleUsuarioADetalleUsuarioDTO(detalleUsuario);
        } catch(DetalleUsuarioException e) {
            throw new DetalleUsuarioException(e.getMessage());
        }
    }

    @Override
    public DetalleUsuarioDTO registrarDetalleUsuario(DetalleUsuarioCreateDTO detalleUsuario) {
        DetalleUsuario nuevoDetalleUsuario= detalleUsuarioRepository.save(DetalleUsuarioMapper.instancia.detalleUsuarioCreateDTOADetalleUsuario(detalleUsuario));
        return DetalleUsuarioMapper.instancia.detalleUsuarioADetalleUsuarioDTO(nuevoDetalleUsuario);
    }

    @Override
    public DetalleUsuarioDTO actualizarDetalleUsuario(Long id, DetalleUsuarioUpdateDTO detalleUsuario) throws DetalleUsuarioException {
        try {
            DetalleUsuario usuarioExiste = detalleUsuarioRepository.findById(id)
                    .orElseThrow(() -> new DetalleUsuarioException(DetalleUsuarioException.NotFoundException(id)));

            DetalleUsuario detalleUsuarioActualizado = detalleUsuarioRepository.save(DetalleUsuarioMapper.instancia.detalleUsuarioUpdateDTOADetalleUsuario(detalleUsuario));
            return DetalleUsuarioMapper.instancia.detalleUsuarioADetalleUsuarioDTO(detalleUsuarioActualizado);

        } catch (DetalleUsuarioException e) {
            throw new DetalleUsuarioException(e.getMessage());
        }
    }
}
