package com.salesianos.triana.dam.Miarma.service.impl;

import com.salesianos.triana.dam.Miarma.dto.ConverterPostDto;
import com.salesianos.triana.dam.Miarma.dto.CreatePostDto;
import com.salesianos.triana.dam.Miarma.dto.GetPostDto;
import com.salesianos.triana.dam.Miarma.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.Miarma.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.Miarma.model.Post;
import com.salesianos.triana.dam.Miarma.model.Public;
import com.salesianos.triana.dam.Miarma.repo.PostRepository;
import com.salesianos.triana.dam.Miarma.service.PostService;
import com.salesianos.triana.dam.Miarma.service.StorageService;
import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import com.salesianos.triana.dam.Miarma.users.service.impl.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final ConverterPostDto converter;
    private final StorageService storageService;
    private final UserEntityService userEntityService;

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
    public List<GetPostDto> findAllPublic() {
        List<Post> data = repository.findAllPostPublic();

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Post.class);
        }else {
            return data.stream().map(converter::getPostToDto).collect(Collectors.toList());
        }
    }

    @Override
    public Post createPostPublic(CreatePostDto createPostDto, MultipartFile file, Usuario usuario) throws IOException {
        String filename = storageService.store(file);

        String extension = StringUtils.getFilenameExtension(filename);

        BufferedImage img = ImageIO.read(file.getInputStream());

        BufferedImage imgScale = storageService.resizer(img, 1024);

        OutputStream out = Files.newOutputStream(storageService.load(filename));

        ImageIO.write(imgScale, extension, out);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();
        String uri2 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(storageService.store(file))
                .toUriString();

        return repository.save(Post.builder()
                .titulo(createPostDto.getTitulo())
                .texto(createPostDto.getTexto())
                .publica(createPostDto.getAPublic())
                .file(uri2)
                .reescalada(uri)
                .usuario(userEntityService.findUserById(usuario.getId()))
                .build());
    }

    @Override
    public Post findById(Long id) {
        return repository.findOnePublic(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Post.class));
    }

    public List<GetPostDto> findAllByUser(@RequestParam(name = "username") String username) {
        List<Post> data = repository.findAllByUser(username);

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Post.class);
        }else {
            return data.stream().map(converter::getPostToDto).collect(Collectors.toList());
        }
    }

    public List<GetPostDto> findAllByLoggedUser(@AuthenticationPrincipal Usuario usuario) {
        List<Post> data = repository.findAllByLoggedUser(usuario.getUsername());

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Post.class);
        }else {
            return data.stream().map(converter::getPostToDto).collect(Collectors.toList());
        }
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
            c.setFile(uri);
            return repository.save(c);
        }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Post.class));

    }

    public ResponseEntity<?> delete (@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) throws IOException {

        Post post = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Post.class));
        if(usuario.getId().equals(post.getUsuario().getId())){
            storageService.deleteFile(post.getFile());
            storageService.deleteFile(post.getReescalada());
            repository.delete(post);
        }else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();

    }
}
