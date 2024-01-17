package com.springmvc.service;

import java.util.List;
import java.util.Set;

import com.springmvc.entities.Produto;

public interface ProdutoService {
	
	List<Produto> getAllCProduto();

	Produto getProdutoById(Long id);

	Produto saveProduto(Produto produto, Set<Long> categoriaIds);
	
	Produto updateProduto(Long id, Produto produtoAtualizado);

	void deleteProdutoa(Long id);
}
