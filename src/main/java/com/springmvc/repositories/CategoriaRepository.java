package com.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
