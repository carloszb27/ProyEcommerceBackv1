package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.entities.User;
import com.proyectos.ProyectoEcommerce.error.exceptions.UserException;
import com.proyectos.ProyectoEcommerce.repositories.UserRepository;
import com.proyectos.ProyectoEcommerce.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<User> listarUsers() {
        List<User> lista = userRepository.findAllByActiveTrue();

        return lista.size()>0 ? lista : new ArrayList<User>();
    }

    @Override
    public User listarUserPorId(Long id) throws UserException {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserException.NotFoundException(id)));

        return usuario;
    }

    @Override
    public User registrarUser(User usuario) {

        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()) );
        
        return userRepository.save(usuario);
    }

    @Override
    public User actualizarUser(Long id, User usuario) throws UserException {

        User usuarioExiste = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserException.NotFoundException(usuario.getId())));

        return userRepository.save(usuario);
    }

    @Override
    public String eliminarUser(Long id) throws UserException {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserException.NotFoundException(id)));

        userRepository.updateUserSetActiveForId(false, id);

        return "El user se ha eliminado correctamente";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con dicho email: " + username + "no existe."));

        return new UserDetailImplement(user);
    }

}
