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

import com.academico.models.model.Professor;
import com.academico.models.service.ProfessorService;

@RestController
@RequestMapping(value = "/rest/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody Professor professor) {
	     var professorGravado = professorService.save(professor);
	     return ResponseEntity
	    		 .status(HttpStatus.CREATED.value())
	    		 .body(professorGravado);
	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Professor professor) {
	     System.out.println(professor.toString()); 
		 var professorAlterado = professorService.update(id, professor);
	     return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(professorAlterado);
	}

	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
	     professorService.deleteById(id);
	     return ResponseEntity
	    		 .status(HttpStatus.NO_CONTENT.value())
	    		 .body("Professor excluído com sucesso!");
	}
	
	
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
	     var professorCadastrada = professorService.findById(id);
	     return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(professorCadastrada);
	}
	
	
	

}
