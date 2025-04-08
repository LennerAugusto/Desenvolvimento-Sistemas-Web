package com.projeto.models.service.componentes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class CriptografarSenha {
	private PasswordEncoder passwordEncoder;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public PasswordEncoder getPassowordEncoder() {
		return passwordEncoder;
		
	}
}
