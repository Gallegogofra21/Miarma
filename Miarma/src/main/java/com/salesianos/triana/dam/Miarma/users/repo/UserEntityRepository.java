package com.salesianos.triana.dam.Miarma.users.repo;

import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);

    Optional<Usuario> findById(Long id);

    @Query(value = """
            SELECT * FROM USERS
            WHERE USERS.PERFIL = 0
            AND USERS.ID = :id
            """, nativeQuery = true)
    Optional<Usuario> findUserPublic(Long id);

    boolean existsByUsername(String username);
}
