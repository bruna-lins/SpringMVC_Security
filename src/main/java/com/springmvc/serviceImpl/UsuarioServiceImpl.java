package com.springmvc.serviceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springmvc.dto.UsuarioDTO;
import com.springmvc.entities.Role;
import com.springmvc.entities.Usuario;
import com.springmvc.repositories.RoleRepository;
import com.springmvc.repositories.UsuarioRepository;
import com.springmvc.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<UsuarioDTO> getAllUsuario() {
		List<Usuario> users = userRepository.findAll();
		return users.stream().map((user) -> 
		mapToUserDto(user)).collect(Collectors.toList());
	}

	private UsuarioDTO mapToUserDto(Usuario user) {
		UsuarioDTO userDto = new UsuarioDTO();
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

	@Override
	public void saveUser(UsuarioDTO userDto) {
		Usuario user = new Usuario();
		user.setName(userDto.getName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		Role role = roleRepository.findByAuthority("ROLE_COMUM");
		
		if (role == null) {
	        throw new IllegalStateException("Role 'ROLE_COMUM' não foi encontrada no banco.");
	    }
		
		user.setRoles((List<Role>) Arrays.asList(role));
		userRepository.save(user);
		
	}

	@Override
	public Usuario findUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Usuario user = userRepository.findByName(name);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
					mapRolesToAuthorities(user.getRoles()));
		} else {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<? extends GrantedAuthority> mapRoles = roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
		return mapRoles;
	}

}
