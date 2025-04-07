package com.projeto.models.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.projeto.models.data.LoginRequest;
import com.projeto.models.data.LoginResponse;
import com.projeto.models.model.Usuario;
import com.projeto.models.repository.UsuarioRepository;
import com.projeto.models.service.JwtTokenServiceProvider;
import com.projeto.models.service.LoginService;
import com.projeto.models.service.RefreshTokenService;
import com.projeto.models.service.mapper.ConverterEntity;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UsuarioRepository ususarioRepository;
	
	@Autowired
	private ConverterEntity converter;
	
	@Autowired
	private JwtTokenServiceProvider jwtTokenServiceProvider;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Override
	public LoginResponse login(LoginRequest login) {
		
		Optional<Usuario> usuarioCadastrado = ususarioRepository.findUsuarioByEmail(login.getEmail());
		
		if(!usuarioCadastrado.isPresent()) {
			throw  new UsernameNotFoundException("Email informado não está cadastrado "+ login.getEmail());
		}
		
		Usuario usuario = usuarioCadastrado.get();
		
		if(login.getEmail().equals(usuario.getEmail()) && usuario.isAtivo() == false) {
			throw new LockedException("Usuario esta bloqueado no sistema!");
		}
		
		if(login.getEmail().equals(usuario.getEmail()) && BCrypt.checkpw(login.getPassword(), usuario.getPassword())) {
			new UsernamePasswordAuthenticationToken(usuario, usuario.getPassword(), usuario.getAuthorities());
		}else {
			throw new BadCredentialsException("A senha informada nao é valida!");
		}
		
		var loginResponse = converter.parseObject(usuario, LoginResponse.class);
		
		loginResponse.setAccess_token(jwtTokenServiceProvider.createAccessToken(usuario));
		
		loginResponse.setRefresh_token(jwtTokenServiceProvider.createRefreshToken(usuario));
		
		refreshTokenService.createRefreshToken(usuario, jwtTokenServiceProvider.createAccessToken(usuario));
		
		return loginResponse;
	}

	@Override
	public LoginResponse refreshkToken(String token) {
		
		return null;
	}

	@Override
	public void Logout(String token) {
		
	}

	@Override
	public LoginResponse validarToken(String access_token, String refreshToken) {
		
		return null;
	}

}
