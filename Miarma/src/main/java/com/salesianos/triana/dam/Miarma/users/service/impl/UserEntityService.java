package com.salesianos.triana.dam.Miarma.users.service.impl;

import com.salesianos.triana.dam.Miarma.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.Miarma.errores.exception.SingleEntityNotFoundException2;
import com.salesianos.triana.dam.Miarma.service.BaseService;
import com.salesianos.triana.dam.Miarma.service.StorageService;
import com.salesianos.triana.dam.Miarma.users.dto.CreateUserDto;
import com.salesianos.triana.dam.Miarma.users.model.Usuario;
import com.salesianos.triana.dam.Miarma.users.repo.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<Usuario, Long, UserEntityRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final StorageService storageService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " no encontrado."));
    }

    public Optional<Usuario> loadUserById (Long id) throws UsernameNotFoundException {
        return this.repositorio.findById(id);
    }

    public Usuario findUserById(Long id){
        return repositorio.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Usuario.class));
    }

    public Usuario findUserPublic(Long id){
        return repositorio.findUserPublic(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Usuario.class));
    }

    public Usuario saveUser (@Valid CreateUserDto newUser, MultipartFile file) {

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        if(newUser.getPassword().contentEquals(newUser.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(uri)
                    .nombre(newUser.getNombre())
                    .email(newUser.getEmail())
                    .perfil(newUser.getPerfil())
                    .build();
            return save(usuario);
            }else {
            return null;
        }
    }

    public Usuario edit (CreateUserDto newUser, MultipartFile file, Usuario currentUser) throws IOException {

        String email = currentUser.getEmail();

        String filename = storageService.store(file);

        String extension = StringUtils.getFilenameExtension(filename);

        BufferedImage img = ImageIO.read(file.getInputStream());

        BufferedImage imgScale = storageService.resizer(img, 128);

        OutputStream out = Files.newOutputStream(storageService.load(filename));

        ImageIO.write(imgScale, extension, out);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        return repositorio.findFirstByEmail(currentUser.getEmail()).map(c -> {
            c.setNombre(newUser.getNombre());
            c.setEmail(newUser.getEmail());
            c.setUsername(newUser.getUsername());
            c.setPassword(newUser.getPassword());
            c.setPassword2(newUser.getPassword2());
            c.setAvatar(uri);
            return repositorio.save(c);

        }).orElseThrow(() -> new SingleEntityNotFoundException2(email, Usuario.class));

    }

    public boolean comprobarUsername(String username) {
        return repositorio.existsByUsername(username);
    }

    public boolean comprobarEmail(String email){ return repositorio.existsByEmail(email);}
}
