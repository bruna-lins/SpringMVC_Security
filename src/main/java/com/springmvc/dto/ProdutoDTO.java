package com.springmvc.dto;

import java.util.Set;

public class ProdutoDTO {
	private Long id;
	private String nome;
	private String descricao;
	private Set<Long> categoriaIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Long> getCategoriaIds() {
		return categoriaIds;
	}

	public void setCategoriaIds(Set<Long> categoriaIds) {
		this.categoriaIds = categoriaIds;
	}

}
