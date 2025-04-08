package com.projeto.models.service;

import com.projeto.models.data.LoginRequest;
import com.projeto.models.data.LoginResponse;

public interface LoginService {

	public LoginResponse login(LoginRequest login);
	
	public LoginResponse refreshkToken(String token);
	
	public void Logout(String token);
	
	public LoginResponse validarToken(String access_token, String refreshToken);
	
	
}
