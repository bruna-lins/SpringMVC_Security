package com.springmvc.service;

import java.util.List;

import com.springmvc.dto.UsuarioDTO;
import com.springmvc.entities.Usuario;

public interface UsuarioService {
	
	List<UsuarioDTO> getAllUsuario();
	   
	void saveUser(UsuarioDTO userDto);

	Usuario findUserByName(String name);
}
