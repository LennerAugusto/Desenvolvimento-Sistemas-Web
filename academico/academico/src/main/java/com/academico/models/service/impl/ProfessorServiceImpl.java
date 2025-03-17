package com.academico.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Professor;
import com.academico.models.repository.ProfessorRepository;
import com.academico.models.service.ProfessorService;
import com.academico.models.service.exception.EntityNotFoundException;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	
	
	@Override
	public Professor save(Professor entity) {
		return professorRepository.saveAndFlush(entity);
	}

	@Override
	public Professor update(Long id, Professor entity) {
		var professorCadastrado = findById(id);
		professorCadastrado.setCodProfessor(entity.getCodProfessor());
		professorCadastrado.setNomeProfessor(entity.getNomeProfessor());
		return save(professorCadastrado);
	}

	@Override
	public void deleteById(Long id) {
		professorRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Professor findById(Long id) {
		return professorRepository.findById(id)
				.orElseThrow(
					()-> new EntityNotFoundException(
							String.format("Professor não localizada %s", String.valueOf(id))));
	}

	@Override
	public List<Professor> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
