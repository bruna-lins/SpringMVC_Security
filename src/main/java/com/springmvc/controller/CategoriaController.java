package com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.entities.Categoria;
import com.springmvc.service.CategoriaService;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public String listCategorias(Model model) {
		List<Categoria> categorias = categoriaService.getAllCategorias();
		model.addAttribute("categorias", categorias);
		return "ListarCategorias";
	}

	@GetMapping("/novo")
	public String showFormForAdd(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		return "categoriaForm";
	}

	@PostMapping("/save")
	public String saveCategoria(@ModelAttribute("categoria") Categoria categoria) {
		categoriaService.saveCategoria(categoria);
		return "redirect:/categorias";
	}

	@GetMapping("/editar/{id}")
	public String showFormForUpdate(@PathVariable Long id, Model model) {
		Categoria categoria = categoriaService.getCategoriaById(id);
		model.addAttribute("categoria", categoria);
		return "editarCategoria";
	}

	@PostMapping("/update/{id}")
	public String updateCategoria(@PathVariable("id") Long id, @ModelAttribute("categoria") Categoria categoria) {
		categoriaService.updateCategoria(id, categoria);
		return "redirect:/categorias";
	}

	@GetMapping("/deletar/{id}")
	public String deleteCategoria(@PathVariable Long id) {
		categoriaService.deleteCategoria(id);
		return "redirect:/categorias";
	}
}
