package com.academico.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.models.model.Aluno;
import com.academico.models.service.AlunoService;

@RestController
@RequestMapping(value = "/rest/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody Aluno aluno) {
	     var alunoGravado = alunoService.save(aluno);
	     return ResponseEntity
	    		 .status(HttpStatus.CREATED.value())
	    		 .body(alunoGravado);
	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Aluno aluno) {
	     System.out.println(aluno.toString()); 
		 var alunoAlterado = alunoService.update(id, aluno);
	     return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(alunoAlterado);
	}

	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
	     alunoService.deleteById(id);
	     return ResponseEntity
	    		 .status(HttpStatus.NO_CONTENT.value())
	    		 .body("Aluno excluído com sucesso!");
	}
	
	
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
	     var alunoCadastrado = alunoService.findById(id);
	     return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(alunoCadastrado);
	}
	
	
	

}
