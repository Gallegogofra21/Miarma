package com.salesianos.triana.dam.Miarma.users.service.impl;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
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

    public Usuario saveUser (CreateUserDto newUser, MultipartFile file) {

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        if(newUser.getPassword().contentEquals(newUser.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(newUser.getPassword2()))
                    .avatar(uri)
                    .nombre(newUser.getNombre())
                    .email(newUser.getEmail())
                    .build();
            return save(usuario);
            }else {
            return null;
        }
    }
}