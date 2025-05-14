package com.proyectos.ProyectoEcommerce.service.implementation;

import com.proyectos.ProyectoEcommerce.persistence.entity.Role;
import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@RequiredArgsConstructor
public class UserDetailImplement implements UserDetails{

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //return new ArrayList<SimpleGrantedAuthority>();

        Set<Role> roles = user.getRoles();

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for(Role role : roles) {
            switch (role.getRoleEnum()) {
                case ADMIN -> authorities.addAll(Set.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_EMPLOYEE"),
                        new SimpleGrantedAuthority("ROLE_USER")
                ));
                case EMPLOYEE -> authorities.addAll(Set.of(
                        new SimpleGrantedAuthority("ROLE_EMPLOYEE"),
                        new SimpleGrantedAuthority("ROLE_USER")
                ));
                case USER -> authorities.addAll(Set.of(
                        new SimpleGrantedAuthority("ROLE_USER")
                ));
            };
        }
        return authorities;
        
//        return switch (user.getRol()) {
//            case ADMIN -> Set.of(
//                    new SimpleGrantedAuthority("ROLE_ADMIN"),
//                    new SimpleGrantedAuthority("ROLE_EMPLOYEE"),
//                    new SimpleGrantedAuthority("ROLE_USER")
//            );
//            case EMPLOYEE -> Set.of(
//                    new SimpleGrantedAuthority("ROLE_EMPLOYEE"),
//                    new SimpleGrantedAuthority("ROLE_USER")
//            );
//            case USER -> Set.of(
//                    new SimpleGrantedAuthority("ROLE_USER")
//            );
//        };

//
//        Set<SimpleGrantedAuthority> autorities = new HashSet<>();
//
//        if(user.getRol() == USER) {
//
//            autorities.add(new SimpleGrantedAuthority(USER.toString()));
//        } else if(user.getRol() == ADMIN) {
//
//            SimpleGrantedAuthority admin = new SimpleGrantedAuthority("ADMIN");
//            SimpleGrantedAuthority user = new SimpleGrantedAuthority("USER");
//            SimpleGrantedAuthority employee = new SimpleGrantedAuthority("EMPLOYEE");
//
//            autorities = Set.of(admin, user, employee);
//        } else if(user.getRol() == EMPLOYEE) {
//            SimpleGrantedAuthority user = new SimpleGrantedAuthority("USER");
//            SimpleGrantedAuthority employee = new SimpleGrantedAuthority("EMPLOYEE");
//
//            autorities = Set.of(user, employee);
//        }
//
//        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}