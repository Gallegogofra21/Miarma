package com.salesianos.triana.dam.Miarma.controller;

import com.salesianos.triana.dam.Miarma.dto.CreatePostDto;
import com.salesianos.triana.dam.Miarma.dto.GetPostDto;
import com.salesianos.triana.dam.Miarma.model.Post;
import com.salesianos.triana.dam.Miarma.service.PostService;
import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public Post findOne(@PathVariable Long id) {
        return postService.findById(id);
    }

    @GetMapping("/post/{username}")
    public ResponseEntity<?> findAllByUser(@RequestParam String username) {
        return ResponseEntity.ok(postService.findAllByUser(username));
    }

    @PostMapping("/")
    public ResponseEntity<?> create (@RequestPart("file")MultipartFile file, @RequestPart("post")CreatePostDto newPost, @AuthenticationPrincipal Usuario usuario) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPostPublic(newPost, file, usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPost (@PathVariable Long id, @RequestPart("post") CreatePostDto newPost, @RequestPart("file") MultipartFile file){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.edit(newPost, file, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost (@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) throws IOException {
        postService.delete(id, usuario);
        return ResponseEntity.noContent().build();
    }


}
