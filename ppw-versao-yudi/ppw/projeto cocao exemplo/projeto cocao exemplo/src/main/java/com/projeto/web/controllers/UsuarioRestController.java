package com.projeto.web.controllers;

import com.projeto.config.ConfigProjeto;
import com.projeto.models.data.UsuarioRequest;
import com.projeto.models.data.UsuarioResponse;
import com.projeto.models.data.UsuarioRoleResponse;
import com.projeto.models.service.UsuarioService;
import com.projeto.web.assembler.UsuarioModelAssembler;
import com.projeto.web.response.MensagemSistema;
import com.projeto.web.swagger.UsuarioRestControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/rest/usuario")
public class UsuarioRestController implements UsuarioRestControllerApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @Autowired
    private MensagemSistema<UsuarioResponse> mensagem;

    @Autowired
    private MensagemSistema<UsuarioRoleResponse> mens;

    @Override
    @GetMapping(value="/listar", 
         produces = { MediaType.APPLICATION_JSON_VALUE, 
                      MediaType.APPLICATION_XML_VALUE})
    public CollectionModel<EntityModel<UsuarioResponse>> lista(){
        List<EntityModel<UsuarioResponse>> usuarios = usuarioService.list().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioRestController.class).lista()).withSelfRel());
    }

    @Override
    @GetMapping(value="/listapaginada")
    public Page<UsuarioResponse> listaPaginada(  
               @RequestParam(value="page", 
                             defaultValue = ConfigProjeto.PAGE_NUMBER, 
                             required = false) 
                             Integer page,
               @RequestParam(value="pageSize",
                            defaultValue = ConfigProjeto.PAGE_SIZE,
                            required = false) Integer pageSize,
               @RequestParam(value="dir",
                             defaultValue = ConfigProjeto.SORT,
                             required = false) String  dir,
               @RequestParam(value="props",
                             defaultValue = ConfigProjeto.PROPS,
                             required = false) String  props ) {
    
        return usuarioService.listPagination(page, pageSize, dir, props);
    }

    @Override
    @GetMapping(value = "/buscar/{id}", 
               produces = { MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<UsuarioResponse>> buscarUsuarioPorId(@PathVariable("id") Long id) {
        var usuarioResponse = usuarioService.read(id); 
        EntityModel<UsuarioResponse> usuarioModel = assembler.toModel(usuarioResponse);

        mensagem.showMensagem("Usuário encontrado com sucesso!",
               HttpStatus.OK.value(),
               usuarioResponse);
        return ResponseEntity.ok(usuarioModel);
    }

    @Override
    @GetMapping(value = "/{id}/roles", 
               produces = { MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<UsuarioRoleResponse>> buscarUsuarioRolePorId(@PathVariable("id") Long id) {
        var usuarioRoleResponse = usuarioService.findUsuarioRoleById(id);
        EntityModel<UsuarioRoleResponse> usuarioRoleModel = EntityModel.of(usuarioRoleResponse);

        mens.showMensagem("Usuário encontrado com sucesso!",
               HttpStatus.OK.value(),
               usuarioRoleResponse);
        return ResponseEntity.ok(usuarioRoleModel);
    }

    @Override
    @PostMapping(value="/salvar", 
               consumes = { MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE }, 
               produces = { MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<UsuarioResponse>> salvarUsuario(@RequestBody UsuarioRequest usuario ) {
        var usuarioResponse = usuarioService.save(usuario);
        EntityModel<UsuarioResponse> usuarioModel = assembler.toModel(usuarioResponse);

        mensagem.showMensagem("Usuário cadastrado com sucesso!",
               HttpStatus.CREATED.value(),
               usuarioResponse);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(usuarioModel);
    }

    @Override
    @PutMapping(value="/alterar/{id}", 
               consumes = { MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE }, 
               produces = { MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<UsuarioResponse>> alterarUsuario(@PathVariable("id") Long id, @RequestBody UsuarioRequest usuario) {
        var usuarioResponse = usuarioService.update(id, usuario);
        EntityModel<UsuarioResponse> usuarioModel = assembler.toModel(usuarioResponse);

        mensagem.showMensagem("Usuário alterado com sucesso!",
               HttpStatus.OK.value(),
               usuarioResponse);
        return ResponseEntity.status(HttpStatus.OK.value()).body(usuarioModel);
    }

    @Override
    @DeleteMapping(value="/excluir/{id}", 
               consumes = { MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE }, 
               produces = { MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> excluirUsuario(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        mensagem.showMensagem("Usuário excluído com sucesso!",
                   HttpStatus.NO_CONTENT.value(),
                   null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body(mensagem);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestParam String email) {
        usuarioService.sendPasswordResetEmail(email);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        usuarioService.resetPassword(token, newPassword);
    }

    @Override
	@GetMapping(value="/listaPaginadaPorNome")
	public ResponseEntity<?> listaPaginadaPorNome(String key, 
			@RequestParam(value="page", 
            		      defaultValue = ConfigProjeto.PAGE_NUMBER, 
                          required = false) 
                          Integer page,
            @RequestParam(value="pageSize",
	   		              defaultValue = ConfigProjeto.PAGE_SIZE,
	   		              required = false) 
	                      Integer pageSize,
            @RequestParam(value="dir",
                          defaultValue = ConfigProjeto.SORT,
                          required = false) 
	                      String  dir,
            @RequestParam(value="props",
			              defaultValue = ConfigProjeto.PROPS,
				          required = false) 
	                      String  props ) {
		
		var usuarioResponse = usuarioService.listPaginationWithKey(key, page, pageSize, dir, props);
		
		return ResponseEntity.status(HttpStatus.OK.value()).body(usuarioResponse);
	}
}
