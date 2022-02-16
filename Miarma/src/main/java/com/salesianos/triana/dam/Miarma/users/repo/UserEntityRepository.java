package com.salesianos.triana.dam.Miarma.users.repo;

import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);

    Optional<Usuario> findById(Long id);

    Usuario findUserById(Long id);
}
