package com.academico.models.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
@Table(name = "USUARIO")
public class Usuario {

	private Long   idUsuario;
	private String codUsuario;
	private String nomeUsuario;
	private String email;
	private String senha;
	private String foto;
	private Integer tipo;
	private Cidade cidade;
	private Professor professor;
	private Aluno     aluno;
	

	public Usuario() {

	}

	public Usuario(Long idUsuario, String codUsuario, String nomeUsuario, String email, String senha, String foto, Integer tipo) {
		this.idUsuario = idUsuario;
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
		this.tipo = tipo;
	}
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCIA_USUARIO",initialValue = 1, allocationSize = 1, name = "USUARIO_SEQ")
	@Column(name = "ID_USUARIO")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Column(name = "COD_USUARIO")
	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	@Column( name = "NOME_USUARIO" )
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="SENHA")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name="FOTO")
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Column(name="TIPO", nullable = false)
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CIDADE",referencedColumnName = "ID_CIDADE")
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@OneToOne(mappedBy = "usuario",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
    @OneToOne(mappedBy = "usuario", 
    		  fetch = FetchType.LAZY,
    		  cascade = CascadeType.ALL,
    		  orphanRemoval = true )
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(idUsuario, other.idUsuario);
	}

	@Override
	public String toString() {
		return "Usuario [codUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario + ", email=" + email + ", senha="
				+ senha + ", foto=" + foto + ", tipo=" + tipo + "]";
	}

	
	
	
	
}
