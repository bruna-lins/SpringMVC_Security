package com.springmvc.service;

import java.util.List;

import com.springmvc.entities.Categoria;

public interface CategoriaService {
	List<Categoria> getAllCategorias();

	Categoria getCategoriaById(Long id);

	Categoria saveCategoria(Categoria categoria);

	Categoria updateCategoria(Long id, Categoria categoriaAtualizada);

	void deleteCategoria(Long id);
}
