package com.proyectos.ProyectoEcommerce.service.implementation;

import com.proyectos.ProyectoEcommerce.persistence.entity.Role;
import com.proyectos.ProyectoEcommerce.persistence.enums.RoleEnum;
import com.proyectos.ProyectoEcommerce.persistence.repository.RoleRepository;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.User.UserUpdateDTO;
import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.service.exception.UserException;
import com.proyectos.ProyectoEcommerce.util.mapper.UserMapper;
import com.proyectos.ProyectoEcommerce.persistence.repository.UserRepository;
import com.proyectos.ProyectoEcommerce.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()) );

        Set<Role> roles = new HashSet<>();

        for(String rolestr : userCreateDTO.roles()) {

            RoleEnum roleEnum = RoleEnum.valueOf(rolestr.toUpperCase());

            Role role = roleRepository.findByRoleEnum(roleEnum)
                    .orElseThrow(() -> new RuntimeException("Role no existe"));

//                    .orElseGet(() -> {
//                        // Si no existe, lo crea y guarda
//                        Role nuevoRol = Role.builder()
//                                .roleEnum(roleEnum)
//                                .build();
//                        return roleRepository.save(nuevoRol);
//                    });

            roles.add(role);
        }

        usuario.setRoles(roles);
        //usuario.setRol(RoleEnum.valueOf(userCreateDTO.rol().toUpperCase()));
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

//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
//
//        user.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

//        return new User(userEntity.getEmail(),
//                userEntity.getPassword(),
//                userEntity.isEnabled(),
//                userEntity.isAccountNoExpired(),
//                userEntity.isCredentialNoExpired()
//                        .userEntity.isAccountNoLocked(),
//                authorityList);
    }

}
