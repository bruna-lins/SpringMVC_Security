package com.springmvc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.entities.Categoria;
import com.springmvc.repositories.CategoriaRepository;
import com.springmvc.service.CategoriaService;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> getAllCategorias() {
		return categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria getCategoriaById(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	@Transactional
	public Categoria updateCategoria(Long id, Categoria categoriaAtualizada) {
		Categoria categoriaExistente = categoriaRepository.findById(id).orElse(null);
		if (categoriaExistente != null) {
			categoriaExistente.setNome(categoriaAtualizada.getNome());
			categoriaExistente.setDescricao(categoriaAtualizada.getDescricao());
			return categoriaRepository.save(categoriaExistente);
		} else {
			throw new RuntimeException("Categoria não encontrada com o ID: " + id);
		}
	}

	@Override
	public void deleteCategoria(Long id) {
		categoriaRepository.deleteById(id);
	}
}
