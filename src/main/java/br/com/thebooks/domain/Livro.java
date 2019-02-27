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
public class Livro extends EntidadeDominio {
	@Id
	@GeneratedValue
	@Column(name="id_livro")
	private int id;
	@Column(name="nome_livro")
	private String nomeLivro;
	private int paginas;
	@OneToOne(cascade=CascadeType.ALL,
			fetch = FetchType.EAGER)
	@JoinColumn(name="id_estilo")
	private Estilo estilo;
	
	public Livro() {}
	
	public Livro(Estilo estilo) {
		super();
		this.estilo = estilo;
	}

	public Livro(String nomeLivro, int paginas, Estilo estilo) {
		super();
		this.nomeLivro = nomeLivro;
		this.paginas = paginas;
		this.estilo = estilo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeLivro() {
		return nomeLivro;
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	
	
	
}
