package com.academico.models.service.dto.response;

public class UsuarioResponse {

	private Long    idUsuario;
	private String  codUsuario;
	private String  nomeUsuario;
	private String  email;
	private Integer tipo;
	private String  nomeCidade;
	private String  foto;
	private String  nomeProfessor;
	private String  codProfessor; 
	private Integer idade;
	private String  nomeAluno;
	private String  codAluno;
	
	
	public UsuarioResponse() {
		super();
	}

	public UsuarioResponse(
			      Long idUsuario, 
			      String codUsuario, 
			      String nomeUsuario, 
			      String email, 
			      Integer tipo) {
		super();
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.tipo = tipo;
	}
	
	public UsuarioResponse(
			Long idUsuario, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
			Integer tipo,
			String nomeCidade, 
			String foto) {
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.tipo = tipo;
		this.nomeCidade = nomeCidade;
		this.foto = foto;
	}

	public UsuarioResponse(
			Long idUsuario, 
			String codUsuario, 
			String nomeUsuario, 
			String email, 
			Integer tipo,
			String nomeCidade, 
			String foto, 
			String nomeProfessor, 
			String codProfessor,
			Integer idade,
			String nomeAluno,
			String codAluno ) {
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.tipo = tipo;
		this.nomeCidade = nomeCidade;
		this.foto = foto;
		this.nomeProfessor = nomeProfessor;
		this.codProfessor = codProfessor;
		this.idade = idade;
		this.nomeAluno = nomeAluno;
		this.codAluno = codAluno;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public Integer getTipo() {
		return tipo;
	}
 
	public String getNomeCidade() {
		return nomeCidade;
	}
	
	public String getFoto() {
		return foto;
	}

	
	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public String getCodProfessor() {
		return codProfessor;
	}
	
	

	public Integer getIdade() {
		return idade;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public String getCodAluno() {
		return codAluno;
	}

	@Override
	public String toString() {
		return "UsuarioResponse [idUsuario=" + idUsuario + ", codUsuario=" + codUsuario + ", nomeUsuario=" + nomeUsuario
				+ ", email=" + email + "]";
	}

	
	
	
	
}
