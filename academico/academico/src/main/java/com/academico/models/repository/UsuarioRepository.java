package com.academico.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.models.model.Usuario;
import com.academico.models.repository.projection.UsuarioCidadeProjection;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

	
	@Query("SELECT "
			+ "u.idUsuario AS idUsuario, "
			+ "u.codUsuario AS codUsuario, "
			+ "u.nomeUsuario AS nomeUsuario, "
			+ "u.email AS email, "
			+ "u.tipo AS tipo, "
			+ "u.foto AS foto, "
			+ "c.nomeCidade AS nomeCidade "
			+ "FROM Usuario u "
			+ "LEFT JOIN u.cidade c ")
	List<UsuarioCidadeProjection> listagemUsuario();
	
}
