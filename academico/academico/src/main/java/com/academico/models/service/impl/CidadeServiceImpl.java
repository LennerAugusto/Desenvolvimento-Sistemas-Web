package com.academico.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.models.model.Cidade;
import com.academico.models.repository.CidadeRepository;
import com.academico.models.service.CidadeService;
import com.academico.models.service.exception.EntityNotFoundException;

@Service
@Transactional
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	@Override
	public Cidade save(Cidade entity) {
		return cidadeRepository.saveAndFlush(entity);
	}

	@Override
	public Cidade update(Long id, Cidade entity) {
		var cidadeCadastrada = findById(id);
		cidadeCadastrada.setCodCidade(entity.getCodCidade());
		cidadeCadastrada.setNomeCidade(entity.getNomeCidade());
		return save(cidadeCadastrada);
	}

	@Override
	public void deleteById(Long id) {
		cidadeRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cidade findById(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(
					()-> new EntityNotFoundException(
							String.format("Cidade não localizada %s", String.valueOf(id))));
	}

	@Override
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

}
