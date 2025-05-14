package com.proyectos.ProyectoEcommerce.persistence.repository;

import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByActiveTrue();

    //public Optional<User> findByUsername(String username);

    @Query("select u from User u where u.detalleUsuario.dni = ?1")
    public User findByDni(String dni);

    Optional<User> findOneByEmail(String email);

    //Optional<User> findOneByUsername(String email);

    @Query("select u from User u where u.detalleUsuario.id = ?1")
    User findByDetalleUsuarioId(Long idDetalleUsuario);

    @Modifying
    @Transactional
    @Query("update User u set u.active =:active where u.id =:id")
    void updateUserSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
