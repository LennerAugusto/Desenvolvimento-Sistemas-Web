package com.projeto.models.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.models.data.UsuarioRequest;
import com.projeto.models.data.UsuarioResponse;
import com.projeto.models.data.UsuarioRoleResponse;
import com.projeto.models.model.Usuario;
import com.projeto.models.repository.UsuarioRepository;
import com.projeto.models.service.EmailService;
import com.projeto.models.service.UsuarioService;
import com.projeto.models.service.componentes.CriptografarSenha;
import com.projeto.models.service.exception.ConfirmPasswordNaoInformado;
import com.projeto.models.service.exception.EmailJaCadastradoException;
import com.projeto.models.service.exception.EntityNotFoundException;
import com.projeto.models.service.exception.PasswordDiferenteConfirmePasswordException;
import com.projeto.models.service.mapper.ConverterEntity;
import com.projeto.models.service.pagination.PageRequestConfig;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired   
	private UsuarioRepository usuarioRepository;
	;
	@Autowired
	private ConverterEntity converter;
	
	@Autowired
	private CriptografarSenha crypt;
	
	@Autowired
	private EmailService emailService;

	
	@Override
	public UsuarioResponse save(UsuarioRequest entity){

		var usuario = converter.parseObject(entity, Usuario.class);
		
		var usuarioCadastrado = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
		
		if (usuarioCadastrado.isPresent() && usuarioCadastrado.get().equals(usuario)) {
			throw new EmailJaCadastradoException("O e-mail informado já está cadastrado!!!");
		}
		
		if(usuario.getPassword().equals("")) {
			throw new ConfirmPasswordNaoInformado("O campo senha deve ser informada");
		}
		
		if (!usuario.getPassword().equals(entity.getConfirmePassword())) {
	        throw new PasswordDiferenteConfirmePasswordException("A senha de confirmção difere da senha informada!!");		
		}
		
		usuario.setPassword(criptografarSenha(usuario.getPassword()));
		
	    usuario = usuarioRepository.save(usuario);
		var usuarioResponse = converter.parseObject(usuario, UsuarioResponse.class);
		return usuarioResponse;
		
	}

	@Override
	public UsuarioResponse update(Long id, UsuarioRequest entity) {
		
		var usuario = converter.parseObject(entity, Usuario.class);
		
		if (!usuario.getPassword().equals(entity.getConfirmePassword())) {
	        throw new PasswordDiferenteConfirmePasswordException("A senha de confirmção difere da senha informada!!");		
		}
		
		var usuarioCadastrado = usuarioRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!")  );
		
		usuario.setPassword(criptografarSenha(usuario.getPassword()));
		usuarioCadastrado.setEmail(usuario.getEmail());
		usuarioCadastrado.setPassword(usuario.getPassword());
		usuarioCadastrado.setUsername(usuario.getUsername());
		
		usuarioCadastrado = usuarioRepository.saveAndFlush(usuarioCadastrado);
	
		var usuarioResponse = converter.parseObject(usuario, UsuarioResponse.class);
		
		return usuarioResponse; 
	}

	@Override
	public void delete(Long id) {
	    usuarioRepository.deleteById(id);	
	}


	@Override
	@Transactional(readOnly = true) 
	public UsuarioResponse read(Long id) {
		var usuario = usuarioRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!")  );
	    return converter.parseObject(usuario, UsuarioResponse.class);			
	}

	@Override
	@Transactional(readOnly = true) 
	public List<UsuarioResponse> list() {
		var usuario = usuarioRepository.findAll();
		return converter.parseListObjects(usuario, UsuarioResponse.class);   
	}

	@Override
	@Transactional(readOnly = true) 
	public Page<UsuarioResponse> listPaginationWithKey(
			    String key, 
			    Integer page, 
			    Integer pageSize, 
			    String dir, 
			    String props){
		 
		Pageable pagina = PageRequestConfig.gerarPagina(page, pageSize, dir, props);
		
		Page<Usuario> listaUsuario = Objects.isNull(key)
				? usuarioRepository.findAllPagination(pagina)
				: usuarioRepository.findAllPaginationWithKey(key, pagina);		
		
		return converterListaUsuario(listaUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> listPagination(
			   Integer page, 
			   Integer pageSize, 
			   String dir, 
			   String props) {
		
		Pageable pagina = PageRequestConfig.gerarPagina(page, pageSize, dir, props);
		
		Page<Usuario> listaUsuario = usuarioRepository.findAllPagination(pagina);
			
		return converterListaUsuario(listaUsuario);
	}
	
	
	private Page<UsuarioResponse> converterListaUsuario(Page<Usuario> listaUsuario){
		var listaUsuarioResponse = converter.parseListObjects(listaUsuario.getContent(), UsuarioResponse.class); 
		Page<UsuarioResponse> paginaUsuarioResponse = new PageImpl<>(listaUsuarioResponse, 
										                listaUsuario.getPageable(), 
										                listaUsuario.getTotalElements());
		return paginaUsuarioResponse;
		
	}
	
	

	@Override
	public UsuarioRoleResponse findUsuarioRoleById(Long id) {
        var usuario = usuarioRepository.findUsuarioRoleById(id)
        		.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
    	return converter.parseObject(usuario, UsuarioRoleResponse.class);
	}

	private String criptografarSenha(String password) {
		return crypt.getPassowordEncoder().encode(password);		
	}
	
	public void sendPasswordResetEmail(String email) {
	    var usuario = usuarioRepository.findUsuarioByEmail(email)
	            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o email fornecido"));

	    String token = UUID.randomUUID().toString();
	    usuario.setResetToken(token);
	    usuarioRepository.save(usuario);

	    String resetUrl = "http://localhost:8080/reset-password?token=" + token;
	    emailService.sendSimpleMessage(usuario.getEmail(), "Password Reset Request",
	            "Para redefinir sua senha, clique no link abaixo:\n" + resetUrl);
	}

	public void resetPassword(String token, String newPassword) {
	    var usuario = usuarioRepository.findByResetToken(token)
	            .orElseThrow(() -> new EntityNotFoundException("Token inválido ou expirado"));

	    usuario.setPassword(criptografarSenha(newPassword));
	    usuario.setResetToken(null);
	    usuarioRepository.save(usuario);
	}
}
