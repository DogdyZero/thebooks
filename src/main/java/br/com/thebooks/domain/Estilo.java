package br.com.thebooks.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Entity
public class Estilo extends EntidadeDominio {
	@Id
	@GeneratedValue
	@Column(name="id_estilo")
	private int id;
	@Column(name="categoria", unique = true)
	private String categoriaLivro;

	public Estilo() {
		super();
	}
	

	public Estilo(String categoriaLivro) {
		this.categoriaLivro = categoriaLivro;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoriaLivro() {
		return categoriaLivro;
	}

	public void setCategoriaLivro(String categoriaLivro) {
		this.categoriaLivro = categoriaLivro;
	}
	
	
}
