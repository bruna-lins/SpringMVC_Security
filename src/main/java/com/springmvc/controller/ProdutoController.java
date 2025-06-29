package com.springmvc.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.entities.Categoria;
import com.springmvc.entities.Produto;
import com.springmvc.service.CategoriaService;
import com.springmvc.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public String listProdutos(Model model) {
		List<Produto> produto = produtoService.getAllCProduto();
		model.addAttribute("produto", produto);
		return "listProdutos";
	}

	@GetMapping("/novo")
	public String showForm(Model model) {
		Produto produto = new Produto();
		List<Categoria> categorias = categoriaService.getAllCategorias();
		model.addAttribute("produto", produto);
		model.addAttribute("categorias", categorias);
		return "produtoForm";
	}

	@PostMapping("/save")
	public String saveProduto(@ModelAttribute("produto") Produto produto, @RequestParam Set<Long> categorias) {
		produto.setCategorias(categorias.stream().map(categoriaService::getCategoriaById).collect(Collectors.toSet()));
		produtoService.saveProduto(produto, categorias);
		return "redirect:/produtos";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Produto produto = produtoService.getProdutoById(id);
		model.addAttribute("produto", produto);
		model.addAttribute("categorias", categoriaService.getAllCategorias());
		return "editarProduto";
	}

	@PostMapping("/update/{id}")
	public String updateProduto(@PathVariable("id") Long id, @ModelAttribute("produto") Produto produto) {
		produtoService.updateProduto(id, produto);
		return "redirect:/produtos";
	}

	@GetMapping("/deletar/{id}")
	public String deleteProduto(@PathVariable Long id) {
		produtoService.deleteProdutoa(id);
		return "redirect:/produtos";
	}
}
