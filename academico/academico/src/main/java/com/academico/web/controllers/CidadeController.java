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

import com.academico.models.model.Cidade;
import com.academico.models.service.CidadeService;

@RestController
@RequestMapping(value = "/rest/cidade")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(){
		var lista = cidadeService.listar();
		return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(lista);
	}
	
	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody Cidade cidade) {
	     var cidadeGravada = cidadeService.save(cidade);
	     return ResponseEntity
	    		 .status(HttpStatus.CREATED.value())
	    		 .body(cidadeGravada);
	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cidade cidade) {
	     System.out.println(cidade.toString()); 
		 var cidadeAlterada = cidadeService.update(id, cidade);
	     return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(cidadeAlterada);
	}

	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
	     cidadeService.deleteById(id);
	     return ResponseEntity
	    		 .status(HttpStatus.NO_CONTENT.value())
	    		 .body("Cidade excluída com sucesso!");
	}
	
	
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
	     var cidadeCadastrada = cidadeService.findById(id);
	     return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(cidadeCadastrada);
	}
	
	
	

}
