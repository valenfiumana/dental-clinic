package com.example.clinica_dental.repositories;

import com.example.clinica_dental.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findUsuarioByEmail(String email);
}

