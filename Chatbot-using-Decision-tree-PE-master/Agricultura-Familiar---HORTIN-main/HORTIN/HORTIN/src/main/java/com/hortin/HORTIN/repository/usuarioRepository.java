package com.hortin.HORTIN.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hortin.HORTIN.entity.Usuario;
import com.hortin.HORTIN.entity.Vendedor;

public interface usuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("FROM Usuario V WHERE V.Senha = ?1 and V.User = ?2")
	Optional<Usuario> verificaLogin(String senha, String username);
	@Query("FROM Usuario V WHERE V.User = ?1 ")
	Optional<Usuario> getByUser(String User);
}

