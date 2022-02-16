package com.salesianos.triana.dam.Miarma.controller;

import com.salesianos.triana.dam.Miarma.dto.CreatePostDto;
import com.salesianos.triana.dam.Miarma.dto.GetPostDto;
import com.salesianos.triana.dam.Miarma.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public List<GetPostDto> findAll() {
        return postService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> create (@RequestPart("file")MultipartFile file, @RequestPart("post")CreatePostDto newPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(newPost, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPost (@PathVariable Long id, @RequestPart("post") CreatePostDto newPost, @RequestPart("file") MultipartFile file){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.edit(newPost, file, id));
    }


}
