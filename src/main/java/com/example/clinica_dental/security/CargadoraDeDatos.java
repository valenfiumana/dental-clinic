package com.example.clinica_dental.security;

import com.example.clinica_dental.entities.RolUsuario;
import com.example.clinica_dental.entities.Usuario;
import com.example.clinica_dental.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String pass="kiwi";
        String passHash=passwordEncoder.encode(pass);
        Usuario usuario= new Usuario();
        usuario.setNombre("Valen");
        usuario.setEmail("valen@gmail.com");
        usuario.setPassword(passHash);
        usuario.setUsuarioRole(RolUsuario.ROLE_USER);
        usuarioRepository.save(usuario);

        Usuario admin= new Usuario();
        admin.setNombre("Fiu");
        admin.setEmail("fiu@gmail.com");
        admin.setPassword(passHash);
        admin.setUsuarioRole(RolUsuario.ROLE_ADMIN);
        usuarioRepository.save(admin);
    }
}