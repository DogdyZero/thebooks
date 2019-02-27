package br.com.thebooks.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Trofeu extends EntidadeDominio {
	@Id
	@GeneratedValue
	@Column(name="id_trofeu")
	private int id;
	private String nome;
	@Column(name="path_livro")
	private String pathImgTrofeu;
	@OneToOne(cascade=CascadeType.ALL,
			fetch = FetchType.EAGER)
	@JoinColumn(name="id_estilo")
	private Estilo estilo;
	
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
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	
	
	
}
