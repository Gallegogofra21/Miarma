package com.salesianos.triana.dam.Miarma.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    Stream<Path> loadAll();

    Path load (String filename);

    Resource loadAsResource(String filename);

    void deleteFile(String filename);

    void deleteAll();

    BufferedImage resizer(BufferedImage img, int width);
}
