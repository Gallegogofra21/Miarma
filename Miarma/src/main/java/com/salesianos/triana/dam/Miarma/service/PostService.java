package com.salesianos.triana.dam.Miarma.service;

import com.salesianos.triana.dam.Miarma.dto.CreatePostDto;
import com.salesianos.triana.dam.Miarma.dto.GetPostDto;
import com.salesianos.triana.dam.Miarma.model.Post;
import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PostService {

    Post createPostPublic(CreatePostDto createPostDto, MultipartFile file, Usuario usuario) throws IOException;
    List<GetPostDto> findAll();
    Post findById(Long id);
    Post edit(CreatePostDto createPostDto, MultipartFile file, Long id);
    ResponseEntity<?> delete(Long id, Usuario usuario) throws IOException;
    List<GetPostDto> findAllPublic();
    List<GetPostDto> findAllByUser(String username);
    List<GetPostDto> findAllByLoggedUser(Usuario usuario);
}
