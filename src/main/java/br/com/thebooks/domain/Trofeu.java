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
public class Trofeu extends EntidadeDominio {
	@Id
	@GeneratedValue
	@Column(name="id_trofeu")
	private int id;
	private String nome;
	@Column(name="path_livro")
	private String pathImgTrofeu;
	
	@ManyToMany (cascade=CascadeType.ALL,
			fetch=FetchType.LAZY)
	@JoinTable(name="usuario_trofeu",
			joinColumns={@JoinColumn(name="id_trofeu", 
            referencedColumnName="id_trofeu")},  
           inverseJoinColumns={@JoinColumn(name="id_usuario", 
           referencedColumnName="id_usuario")})
	private List<Usuario> usuarios;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPathImgTrofeu() {
		return pathImgTrofeu;
	}
	public void setPathImgTrofeu(String pathImgTrofeu) {
		this.pathImgTrofeu = pathImgTrofeu;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
