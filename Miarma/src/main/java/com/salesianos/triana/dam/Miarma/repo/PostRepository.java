package com.salesianos.triana.dam.Miarma.repo;

import com.salesianos.triana.dam.Miarma.dto.GetPostDto;
import com.salesianos.triana.dam.Miarma.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = """
            SELECT p.*
            FROM POST p
            WHERE p.public = PUBLICA""", nativeQuery = true)
    List<Post> findAllPostPublic();

    @Query(value = """
            SELECT p.*
            FROM POST p LEFT JOIN USUARIO u
            WHERE u.username = :username""", nativeQuery = true)
    List<Post> findAllByUser(String username);
}
