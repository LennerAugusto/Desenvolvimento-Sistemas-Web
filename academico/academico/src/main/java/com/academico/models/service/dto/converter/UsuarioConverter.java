package com.academico.models.service.dto.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.academico.models.model.Usuario;
import com.academico.models.repository.projection.UsuarioCidadeProjection;
import com.academico.models.service.dto.request.UsuarioRequest;
import com.academico.models.service.dto.response.UsuarioResponse;

public class UsuarioConverter {

	public static Usuario toUsuario(UsuarioRequest usuarioRequest) {
		return new Usuario(
					usuarioRequest.getIdUsuario(),
					usuarioRequest.getCodUsuario(),
					usuarioRequest.getNomeUsuario(),
					usuarioRequest.getEmail(),
					usuarioRequest.getSenha(),
					usuarioRequest.getFoto(),
					usuarioRequest.getTipo()
				);
	}
	
	
	public static UsuarioResponse toUsuarioResponse(Usuario usuario) {
		 
		String nomeProfessor = null;
		String codProfessor = null;
		Integer idade = 0; 
		String nomeAluno = null;
		String codAluno = null; 
		
		if (usuario.getTipo()==1) {
			nomeProfessor = usuario.getProfessor().getNomeProfessor();
			codProfessor = usuario.getProfessor().getCodProfessor();
		}else {
			//idade = usuario.getAluno().getIdade();
			//nomeAluno = usuario.getAluno().getNomeAluno();
			//codAluno = usuario.getAluno().getCodAluno();
		}
		
		
		return new UsuarioResponse(
			  	usuario.getIdUsuario(),
			  	usuario.getCodUsuario(),
			  	usuario.getNomeUsuario(),
			  	usuario.getEmail(),
			  	usuario.getTipo(),
			  	usuario.getCidade().getNomeCidade(),
			  	usuario.getFoto(),
			  	nomeProfessor,
			  	codProfessor,
			  	idade,
			  	nomeAluno,
			  	codAluno
		);
	}

	
	public static List<UsuarioResponse> toListUsuarioResponse(List<Usuario> listaUsuario){
		
		List<UsuarioResponse> listaUsuarioResponse = new ArrayList<UsuarioResponse>();
		
	    for(Usuario usuario: listaUsuario) {
	    	UsuarioResponse usuarioResponse = toUsuarioResponse(usuario);
	        listaUsuarioResponse.add(usuarioResponse);	
	    }
		return listaUsuarioResponse;
		
	}


	public static List<UsuarioResponse> projectiontoListUsuarioResponse(List<UsuarioCidadeProjection> listaUsuario) {
		return listaUsuario.stream()
				           .map(projection -> new UsuarioResponse(
				        		   projection.getIdUsuario(),
				        		   projection.getCodUsuario(),
				        		   projection.getNomeUsuario(),
				        		   projection.getEmail(),
				        		   projection.getTipo(),
				        		   projection.getNomeCidade(),
				        		   projection.getFoto()
				        	))
				           .collect(Collectors.toList());
	}
	
	
	
	
}
