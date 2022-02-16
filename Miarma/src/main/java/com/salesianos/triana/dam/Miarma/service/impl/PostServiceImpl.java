package com.salesianos.triana.dam.Miarma.service.impl;

import com.salesianos.triana.dam.Miarma.dto.ConverterPostDto;
import com.salesianos.triana.dam.Miarma.dto.CreatePostDto;
import com.salesianos.triana.dam.Miarma.dto.GetPostDto;
import com.salesianos.triana.dam.Miarma.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.Miarma.exception.SingleEntityNotFoundException;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final ConverterPostDto converter;
    private final StorageService storageService;

    @Override
    public List<GetPostDto> findAll() {
        List<Post> data = repository.findAll();

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Post.class);
        }else {
            return data.stream().map(converter::getPostToDto)
                    .collect(Collectors.toList());
        }
    }

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
    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

    public Post edit(CreatePostDto createPostDto, MultipartFile file, Long id) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        return repository.findById(id).map(c -> {
            c.setTitulo(createPostDto.getTitulo());
            c.setTexto(createPostDto.getTexto());
            c.setPublica(createPostDto.isPublica());
            c.setFile(uri);
            return repository.save(c);
        }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Post.class));

    }
}
