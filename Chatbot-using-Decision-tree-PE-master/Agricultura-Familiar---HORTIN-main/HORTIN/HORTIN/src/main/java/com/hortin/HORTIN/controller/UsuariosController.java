package com.hortin.HORTIN.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.hortin.HORTIN.entity.Produto;
import com.hortin.HORTIN.entity.Usuario;
import com.hortin.HORTIN.entity.Vendedor;
import com.hortin.HORTIN.repository.usuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuariosController {

	@Autowired
	private usuarioRepository usuarioRepo;
		
	@PostMapping()
	public ResponseEntity<Usuario> insereUsuarioRepo(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder){
		System.out.println("a");
		Optional<Usuario> usuarioAchado = usuarioRepo.getByUser(usuario.getUser());
		if(usuarioAchado.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		usuarioRepo.save(usuario);
	
		URI uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuario);
	}
	

	@GetMapping("/login")
	public ResponseEntity<Usuario> verificaLogin(@RequestParam String user,@RequestParam String senha){
		if(usuarioRepo.verificaLogin(senha, user).isEmpty()) {
			System.out.println("Não Autorizado");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println("Autorizado");
		Optional<Usuario> usuarioAchado = usuarioRepo.getByUser(user);
                System.out.println(usuarioAchado);
                System.out.println(usuarioAchado.get());
		return ResponseEntity.status(HttpStatus.OK).body(usuarioAchado.get());
	}
}
