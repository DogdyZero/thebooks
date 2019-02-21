package br.com.thebooks.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario extends EntidadeDominio{
	@Id 
	@GeneratedValue
	@Column(name="id_usuario")
	private int id;
	private String login;
	private String senha;
	private String perfil;
	

	public Usuario() {}

	public Usuario(String login, String senha) {
		this.login = login;
		this.senha= senha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
}
