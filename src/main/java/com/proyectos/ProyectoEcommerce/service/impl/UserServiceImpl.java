package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.dtos.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserDTO;
import com.proyectos.ProyectoEcommerce.dtos.User.UserUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.User;
import com.proyectos.ProyectoEcommerce.error.exceptions.UserException;
import com.proyectos.ProyectoEcommerce.mapper.UserMapper;
import com.proyectos.ProyectoEcommerce.repositories.UserRepository;
import com.proyectos.ProyectoEcommerce.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> listarUsers() {
        List<User> lista = userRepository.findAllByActiveTrue();
        return UserMapper.instancia.listaUserAListaUserDTO(lista);
    }

    @Override
    public UserDTO listarUserPorId(Long id) throws UserException {
        try {
            User usuario = userRepository.findById(id)
                    .orElseThrow(() -> new UserException(UserException.NotFoundException(id)));
            return UserMapper.instancia.userAUserDTO(usuario);

        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }
    }

    @Override
    public UserDTO registrarUser(UserCreateDTO userCreateDTO) {
        User usuario = UserMapper.instancia.userCreateDTOAUser(userCreateDTO);
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()) );
        return UserMapper.instancia.userAUserDTO(userRepository.save(usuario));
    }

    @Override
    public UserDTO actualizarUser(Long id, UserUpdateDTO userUpdateDTO) throws UserException {
        try {
            User usuarioExiste = userRepository.findById(id)
                    .orElseThrow(() -> new UserException(UserException.NotFoundException(id)));
            User usuario = UserMapper.instancia.userUpdateDTOAUser(userUpdateDTO);
            if(usuario.getPassword() == null) {
                usuario.setPassword(usuarioExiste.getPassword());
            }
            return UserMapper.instancia.userAUserDTO(userRepository.save(usuario));
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }

    }

    @Override
    public String eliminarUser(Long id) throws UserException {
        try {
            User usuario = userRepository.findById(id)
                    .orElseThrow(() -> new UserException(UserException.NotFoundException(id)));
            userRepository.updateUserSetActiveForId(false, id);
            return "El user se ha eliminado correctamente";
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con dicho email: " + username + "no existe."));

        return new UserDetailImplement(user);
    }

}
