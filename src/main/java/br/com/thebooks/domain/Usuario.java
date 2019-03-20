package br.com.thebooks.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Usuario extends EntidadeDominio{
	@Id 
	@GeneratedValue
	@Column(name="id_usuario")
	private int id;
	private String login;
	private String senha;
	private String perfil;
	private int pontos;
	@ManyToMany (cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinTable(name="usuario_trofeu",
			joinColumns={@JoinColumn(name="id_usuario", 
            referencedColumnName="id_usuario")},  
           inverseJoinColumns={@JoinColumn(name="id_trofeu", 
           referencedColumnName="id_trofeu")})
	private List<Trofeu> trofeus;
	
	@ManyToMany (cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinTable(name="usuario_livro",
			 joinColumns={@JoinColumn(name="id_usuario", 
	            referencedColumnName="id_usuario")},  
	           inverseJoinColumns={@JoinColumn(name="id_livro", 
	           referencedColumnName="id_livro")})
	private List<Livro> livros;
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

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public List<Trofeu> getTrofeus() {
		return trofeus;
	}

	public void setTrofeus(List<Trofeu> trofeus) {
		this.trofeus = trofeus;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	
	
}
