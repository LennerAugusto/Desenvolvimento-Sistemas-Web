package com.academico.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Aluno;
import com.academico.models.repository.AlunoRepository;
import com.academico.models.service.AlunoService;
import com.academico.models.service.exception.EntityNotFoundException;

@Service
@Transactional
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	
	@Override
	public Aluno save(Aluno entity) {
		return alunoRepository.saveAndFlush(entity);
	}

	@Override
	public Aluno update(Long id, Aluno entity) {
		var alunoCadastrado = findById(id);
		alunoCadastrado.setCodAluno(entity.getCodAluno());
		alunoCadastrado.setNomeAluno(entity.getNomeAluno());
		return save(alunoCadastrado);
	}

	@Override
	public void deleteById(Long id) {
		alunoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno findById(Long id) {
		return alunoRepository.findById(id)
				.orElseThrow(
					()-> new EntityNotFoundException(
							String.format("Aluno não localizada %s", String.valueOf(id))));
	}

	@Override
	public List<Aluno> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
