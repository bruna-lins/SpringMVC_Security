package com.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByName(String name);
}
