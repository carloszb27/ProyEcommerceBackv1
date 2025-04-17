package com.proyectos.ProyectoEcommerce.repositories;

import com.proyectos.ProyectoEcommerce.entities.User;
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

    Optional<User> findOneByEmail(String email);

    User findOneByUsername(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.active =:active where u.id =:id")
    void updateUserSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
