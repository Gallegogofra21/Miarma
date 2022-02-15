package com.salesianos.triana.dam.Miarma.service.impl;

import com.salesianos.triana.dam.Miarma.dto.CreatePostDto;
import com.salesianos.triana.dam.Miarma.model.Post;
import com.salesianos.triana.dam.Miarma.repo.PostRepository;
import com.salesianos.triana.dam.Miarma.service.PostService;
import com.salesianos.triana.dam.Miarma.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final StorageService storageService;

    @Override
    public Post save(CreatePostDto createPostDto, MultipartFile file) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        return repository.save(Post.builder()
                .titulo(createPostDto.getTitulo())
                .texto(createPostDto.getTexto())
                .publica(createPostDto.isPublica())
                .file(uri)
                .build());
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Post edit(CreatePostDto createPostDto, MultipartFile file, Long id) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();
        Optional<Post> postEdit = findById(id);

        return repository.save(postEdit)

    }
}
