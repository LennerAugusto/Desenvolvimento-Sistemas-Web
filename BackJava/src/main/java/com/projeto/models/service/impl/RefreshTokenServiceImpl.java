package com.projeto.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.models.model.RefreshToken;

import com.projeto.models.model.Usuario;
import com.projeto.models.repository.RefreshTokenRepository;
import com.projeto.models.service.RefreshTokenService;

@Service
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Override
	public RefreshToken findByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RefreshToken createRefreshToken(Usuario usuario, String token) {
		RefreshToken refresh = new RefreshToken();
		
		refresh.setUsuario(usuario);
		
		refresh.setToken(token);
		
		refresh.setExpirado(false);
		
		refresh.setBloqueado(false);
		
		refresh = refreshTokenRepository.saveAndFlush(refresh);
		return null;
	}

	@Override
	public RefreshToken refreshToken(String refreshToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTokenByUserId(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTokenBloqueado(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public RefreshToken findTokenById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
