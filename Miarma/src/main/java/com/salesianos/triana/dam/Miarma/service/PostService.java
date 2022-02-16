package com.salesianos.triana.dam.Miarma.service;

import com.salesianos.triana.dam.Miarma.dto.CreatePostDto;
import com.salesianos.triana.dam.Miarma.dto.GetPostDto;
import com.salesianos.triana.dam.Miarma.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post save(CreatePostDto createPostDto, MultipartFile file);
    List<GetPostDto> findAll();
    Optional<Post> findById(Long id);
    Post edit(CreatePostDto createPostDto, MultipartFile file, Long id);
}
