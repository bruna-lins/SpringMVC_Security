package com.springmvc.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.entities.Categoria;
import com.springmvc.entities.Produto;
import com.springmvc.repositories.ProdutoRepository;
import com.springmvc.service.CategoriaService;
import com.springmvc.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaService categoriaService;

	@Override
	public List<Produto> getAllCProduto() {
		return produtoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Produto getProdutoById(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Produto saveProduto(Produto produto, Set<Long> categoriaIds) {
	    // Obtém as categorias correspondentes aos IDs fornecidos
	    Set<Categoria> categorias = categoriaIds.stream()
	            .map(categoriaService::getCategoriaById)
	            .filter(Objects::nonNull) // Filtra categorias não encontradas
	            .collect(Collectors.toSet());

	    produto.setCategorias(categorias);

	    return produtoRepository.save(produto);
	}


	@Override
	@Transactional
	public Produto updateProduto(Long id, Produto produtoAtualizado) {
		Produto produtoExistente = produtoRepository.findById(id).orElse(null);
		if (produtoExistente != null) {
			produtoExistente.setNome(produtoAtualizado.getNome());
			produtoExistente.setDescricao(produtoAtualizado.getDescricao());
			produtoExistente.setPrice(produtoAtualizado.getPrice());
			produtoExistente.setImgUrl(produtoAtualizado.getImgUrl());

			// Atualize as categorias do produto
			Set<Categoria> categoriasAtualizadas = produtoAtualizado.getCategorias();
			for (Categoria categoria : categoriasAtualizadas) {
				categoria.getProdutos().add(produtoExistente);
			}
			produtoExistente.setCategorias(categoriasAtualizadas);

			return produtoRepository.save(produtoExistente);
		} else {
			throw new RuntimeException("Produto não encontrado com o ID: " + id);
		}
	}

	@Override
	public void deleteProdutoa(Long id) {
		produtoRepository.deleteById(id);

	}
	
}