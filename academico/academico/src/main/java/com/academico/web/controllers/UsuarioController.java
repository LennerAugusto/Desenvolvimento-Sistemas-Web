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

import com.academico.models.service.UsuarioService;
import com.academico.models.service.dto.request.UsuarioRequest;

@RestController
@RequestMapping(value = "/rest/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value="/listar")
	public ResponseEntity<?> listar(){
		var lista = usuarioService.listar();
		return ResponseEntity
				 .status(HttpStatus.OK.value())
				 .body(lista);
	}
	
	
	@PostMapping(value="/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody UsuarioRequest usuarioRequest) {
	     var usuarioResponse = usuarioService.save(usuarioRequest);
	     return ResponseEntity
	    		 .status(HttpStatus.CREATED.value())
	    		 .body(usuarioResponse);
	}

	@PutMapping(value="/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
	     var usuarioAlterado = usuarioService.update(id, usuarioRequest);
	     return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(usuarioAlterado);
	}
	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
	     usuarioService.deleteById(id);
	     return ResponseEntity
	    		 .status(HttpStatus.NO_CONTENT.value())
	    		 .body("Usuario excluído com sucesso!");
	}
	
	@GetMapping(value="/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
	     var usuarioCadastrado = usuarioService.findById(id);
	     return ResponseEntity
	    		 .status(HttpStatus.OK.value())
	    		 .body(usuarioCadastrado);
	}
	
	
	

}
