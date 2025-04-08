package com.projeto.models.service.impl;

import java.security.Key;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.projeto.config.ConfigProjeto;
import com.projeto.models.model.Usuario;
import com.projeto.models.service.JwtTokenServiceProvider;
import com.projeto.models.service.exception.InvalidJwtAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtTokenServiceProviderImpl implements JwtTokenServiceProvider {

	private long TOKEN_MILLI_SECONDS =System.currentTimeMillis()+1000;
	
	private long REFRSH_TOKEN_MILLI_SECONDS =System.currentTimeMillis() + 10 * 60 *6000;
	
	private UserDetailsService userDetailsService;
	@Override
	public String createAccessToken(Usuario usuario) {
		
		Claims claims = Jwts.claims();
		claims.put("roles", usuario.getRoles());
		claims.put("foto", usuario.getFoto());
		claims.put("nome", usuario.getUsername());
		claims.put("email", usuario.getEmail());
		
		return generateAccessToken(claims, usuario.getEmail());
	}

	@Override
	public String createRefreshToken(Usuario usuario) {
		
		Claims claims = Jwts.claims();
		claims.put("roles", usuario.getRoles());
		claims.put("foto", usuario.getFoto());
		claims.put("nome", usuario.getUsername());
		claims.put("email", usuario.getEmail());
		
		return generateRefreshToken(claims, usuario.getEmail());
	}

	@Override
	public boolean validateToken(String token) {
		try {
			Date dateExpiration = getDateExpiration(token);
			
			if(dateExpiration.before(new Date())) {
				return false;
			}
			
		}catch(JwtException | IllegalArgumentException e) {
			throw new InvalidJwtAuthenticationException("Token invalido ou expirado");
		}
		
		return true;
	}

	@Override
	public Authentication getAuthentication(String token) {
		UserDetails usuario = FindUsuarioByToken(token);
		
		
		return new UsernamePasswordAuthenticationToken(usuario, "", usuario.getAuthorities());
	}


	@Override
	public String getEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSingKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	@Override
	public Date getDateExpiration(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSingKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
	}

	@Override
	public boolean isTokenValid(String token) {
		UserDetails usuario = FindUsuarioByToken(token);
		Claims claims = getClaims(token);
		String nome = claims.get("nome").toString();
		
		
		return (usuario.getUsername().equals(nome) && !validateToken(token));
	}
	
	private Claims getClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSingKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private String generateAccessToken(Claims claims, String email) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + TOKEN_MILLI_SECONDS))
				.signWith(getSingKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private String generateRefreshToken(Claims claims, String email) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + REFRSH_TOKEN_MILLI_SECONDS))
				.signWith(getSingKey(), SignatureAlgorithm.HS256)
				.compact();
	}


	private Key getSingKey() {
		byte[] keyBytes = Decoders.BASE64.decode(ConfigProjeto.SECRETKEY);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	private UserDetails FindUsuarioByToken(String token) {
		UserDetails usuario = userDetailsService.loadUserByUsername(getEmail(token));
		
		
		return usuario;
	}

}
