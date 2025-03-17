package com.academico.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Aluno;
import com.academico.models.model.Professor;
import com.academico.models.model.Usuario;
import com.academico.models.repository.AlunoRepository;
import com.academico.models.repository.CidadeRepository;
import com.academico.models.repository.ProfessorRepository;
import com.academico.models.repository.UsuarioRepository;
import com.academico.models.repository.projection.UsuarioCidadeProjection;
import com.academico.models.service.UsuarioService;
import com.academico.models.service.dto.converter.UsuarioConverter;
import com.academico.models.service.dto.request.UsuarioRequest;
import com.academico.models.service.dto.response.UsuarioResponse;
import com.academico.models.service.exception.EntityNotFoundException;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public UsuarioResponse save(UsuarioRequest entity) {
		
		var usuario = UsuarioConverter.toUsuario(entity);
		
		var cidade = cidadeRepository.findById(entity.getCodigoCidade());
		
		usuario.setCidade(cidade.get());
		
		usuario = usuarioRepository.saveAndFlush(usuario);
		
		if (entity.getTipo()== 1) {
	    	Professor professor = new Professor();
	    	professor.setNomeProfessor(usuario.getNomeUsuario());
	    	professor.setCodProfessor(usuario.getCodUsuario());
	        professor.setUsuario(usuario);
	        professorRepository.save(professor);
	    		    	
	    }else {
	    
	       Aluno aluno = new Aluno();
	       aluno.setCodAluno(usuario.getCodUsuario());
	       aluno.setNomeAluno(usuario.getNomeUsuario());
	       aluno.setIdade(entity.getIdade());
	       aluno.setUsuario(usuario);
	       alunoRepository.save(aluno);
	    	
	    }
	    
		System.out.println(" USUARIO "+usuario.toString());
		
        
	     
	    return UsuarioConverter.toUsuarioResponse(usuario);
	}

	@Override
	public UsuarioResponse update(Long id, UsuarioRequest entity) {
		var usuario = usuarioRepository.findById(id)
				       .orElseThrow(
						  ()-> new EntityNotFoundException(
						   String.format("Usuario não localizada %s",
						   String.valueOf(id))));
		
		
		usuario.setCodUsuario(entity.getCodUsuario());
		usuario.setNomeUsuario(entity.getNomeUsuario());
		
		if (entity.getTipo()==1) {
			usuario.getProfessor().setCodProfessor(entity.getCodUsuario());
			usuario.getProfessor().setNomeProfessor(entity.getNomeUsuario());
		}else {
			usuario.getAluno().setCodAluno(entity.getCodUsuario());
			usuario.getAluno().setNomeAluno(entity.getNomeUsuario());
		}
		
		usuario = usuarioRepository.saveAndFlush(usuario);
		
		return UsuarioConverter.toUsuarioResponse(usuario);
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioResponse findById(Long id) {
		 var usuario = usuarioRepository.findById(id)
				.orElseThrow(
					()-> new EntityNotFoundException(
							String.format("Usuario não localizada %s",
							String.valueOf(id))));
		 
	     var usuarioResponse = UsuarioConverter.toUsuarioResponse(usuario);
	     
	     return usuarioResponse;
	}

	@Override
	public List<UsuarioResponse> listar() {
		List<UsuarioCidadeProjection> listaUsuario = usuarioRepository.listagemUsuario();
		List<UsuarioResponse> listaUsuarioResponse = UsuarioConverter.projectiontoListUsuarioResponse(listaUsuario); 
		return listaUsuarioResponse;
	}


}
