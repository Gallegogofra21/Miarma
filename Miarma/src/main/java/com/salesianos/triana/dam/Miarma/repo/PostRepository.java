package com.salesianos.triana.dam.Miarma.repo;

import com.salesianos.triana.dam.Miarma.dto.GetPostDto;
import com.salesianos.triana.dam.Miarma.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = """
            SELECT * FROM POST
            WHERE POST.PUBLICA = 0""", nativeQuery = true)
    List<Post> findAllPostPublic();

    @Query(value = """
            SELECT * FROM POST
            WHERE POST.PUBLICA = 0
            AND POST.ID = :id""", nativeQuery = true)
    Optional<Post> findOnePublic(Long id);

    @Query(value = """
            SELECT p.*
            FROM POST p LEFT JOIN USERS u
            WHERE u.username =:username
            AND POST.PUBLICA = 0""", nativeQuery = true)
    List<Post> findAllByUser(String username);

    @Query(value = """
            SELECT p.*
            FROM POST p LEFT JOIN USERS u
            WHERE u.username =:username""", nativeQuery = true)
    List<Post> findAllByLoggedUser(String username);
}
