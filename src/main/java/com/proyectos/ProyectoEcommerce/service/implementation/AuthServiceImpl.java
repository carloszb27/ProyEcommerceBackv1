package com.proyectos.ProyectoEcommerce.service.implementation;

import com.proyectos.ProyectoEcommerce.config.security.JwtService;
import com.proyectos.ProyectoEcommerce.persistence.entity.Auth;
import com.proyectos.ProyectoEcommerce.persistence.entity.AuthResponse;
import com.proyectos.ProyectoEcommerce.persistence.entity.Role;
import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.persistence.enums.RoleEnum;
import com.proyectos.ProyectoEcommerce.persistence.repository.RoleRepository;
import com.proyectos.ProyectoEcommerce.persistence.repository.UserRepository;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.service.interfaces.AuthService;
import com.proyectos.ProyectoEcommerce.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Override
    public AuthResponse register(UserCreateDTO request) {
        log.info("Registrando un usuario");
        User usuario = UserMapper.instancia.userCreateDTOAUser(request);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Role role = roleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Role no existe"));

        usuario.setRoles(Set.of(role));

        User nuevoUser = userRepository.save(usuario);
        String jwtToken = jwtService.generateToken(new UserDetailImplement(nuevoUser));
        return new AuthResponse(jwtToken);
    }

    @Override
    public AuthResponse authenticate(Auth request) throws IOException {
        log.info("Autenticando un usuario");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = userRepository.findOneByEmail(request.getEmail())
                .orElseThrow();

        String jwToken = jwtService.generateToken(new UserDetailImplement(user));

//        LoginLogBuilder.crearLog(user);

        return new AuthResponse(jwToken);
    }
}
