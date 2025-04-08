package com.projeto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projeto.models.service.componentes.CriptografarSenha;
import com.projeto.models.service.security.JwtTokenFilteer;
import com.projeto.models.service.security.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private CriptografarSenha crypt;
	
	@Autowired
	private UsuarioDetailsService userDetailsService;
	
	JwtTokenFilteer jwtTokenFilter() {
		return new JwtTokenFilteer();
	}
	
	@Bean
	SecurityFilterChain filterChainSecurity(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable())
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.securityMatcher("/**")
			.authorizeHttpRequests(configurer->
				configurer.requestMatchers(ConfigProjeto.WHITE_LIST_URL).permitAll()
						  .requestMatchers(HttpMethod.POST,"/rest/login").permitAll()
						  .requestMatchers(HttpMethod.POST,"/rest/usuario/salvar").hasAnyRole("ADMIN", "USER")
						  .requestMatchers(HttpMethod.GET,"/rest/usuario/listar").hasRole("USER")
						  .anyRequest().fullyAuthenticated()
						  );
		
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		
		var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsService)
			   .passwordEncoder(crypt.passwordEncoder());
		
		
		return builder.build();
	}
	
} 
