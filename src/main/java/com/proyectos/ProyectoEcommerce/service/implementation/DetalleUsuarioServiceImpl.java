package com.proyectos.ProyectoEcommerce.service.implementation;

import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.DetalleUsuario.DetalleUsuarioUpdateDTO;
import com.proyectos.ProyectoEcommerce.persistence.entity.DetalleUsuario;
import com.proyectos.ProyectoEcommerce.service.exception.DetalleUsuarioException;
import com.proyectos.ProyectoEcommerce.util.mapper.DetalleUsuarioMapper;
import com.proyectos.ProyectoEcommerce.persistence.repository.DetalleUsuarioRepository;
import com.proyectos.ProyectoEcommerce.service.interfaces.DetalleUsuarioService;
import com.proyectos.ProyectoEcommerce.util.user.SessionUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DetalleUsuarioServiceImpl implements DetalleUsuarioService {
    
    private final DetalleUsuarioRepository detalleUsuarioRepository;

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
    public DetalleUsuarioDTO registrarDetalleUsuario(DetalleUsuarioCreateDTO detalleUsuarioCreate) {
        DetalleUsuario detalleUsuario = DetalleUsuarioMapper.instancia.detalleUsuarioCreateDTOADetalleUsuario(detalleUsuarioCreate);

        validarDetalleUsuario(detalleUsuario);

        Optional<DetalleUsuario> detalleUsuarioExiste = listarDetallePorUserActual();

        detalleUsuarioExiste.ifPresent(usuario ->
                detalleUsuario.setId(usuario.getId()));

        User userActual = SessionUser.getUserAutenticado();

        detalleUsuario.setUser(userActual);

        DetalleUsuario nuevoDetalleUsuario= detalleUsuarioRepository.save(detalleUsuario);
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

    @Override
    public void validarDetalleUsuario(DetalleUsuario detalleUsuario) {

    }

    @Override
    public Optional<DetalleUsuario> listarDetallePorUserActual() {

        User userActual = SessionUser.getUserAutenticado();

        return detalleUsuarioRepository.findByUserId(userActual.getId());
    }


}
