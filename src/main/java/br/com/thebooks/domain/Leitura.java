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
public class Leitura extends EntidadeDominio {
	@Id
	@GeneratedValue
	@Column(name="id_leitura")
	private int id;
	@Column(name="livro_lido")
	private boolean leituraLivro;
	@OneToOne(cascade=CascadeType.ALL,
			fetch = FetchType.EAGER)
	@JoinColumn(name="id_livro")
	private Livro livro;
	@OneToOne(cascade=CascadeType.ALL,
			fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isLeitura() {
		return leituraLivro;
	}
	public void setLeitura(boolean leituraLivro) {
		this.leituraLivro = leituraLivro;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
