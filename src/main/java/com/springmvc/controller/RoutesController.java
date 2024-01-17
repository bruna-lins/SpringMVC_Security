package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springmvc.entities.Produto;
import com.springmvc.service.ProdutoService;

@Controller
public class RoutesController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/")
	public String home(Model model) { 
		List<Produto> produto = service.getAllCProduto();
		model.addAttribute("produto", produto);
		return "homepage";
	}
	
	@GetMapping("/login")
	public String login() { 
		return "login";
	}
}
