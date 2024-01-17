package com.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
