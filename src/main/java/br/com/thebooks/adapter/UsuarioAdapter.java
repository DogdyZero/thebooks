package br.com.thebooks.adapter;

import java.util.List;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Livro;
import br.com.thebooks.domain.Usuario;

public class UsuarioAdapter implements IAdapter{
	private Usuario usuario;
	@Override
	public int processarPaginas(EntidadeDominio entidade) {
		this.usuario = (Usuario)entidade;
		List<Livro> livros = this.usuario.getLivros();
		Livro livro = livros.get(0);
		return livro.getPaginas();
	}

	@Override
	public EntidadeDominio receberEntidade(int pontos) {
		int pontosAntes = this.usuario.getPontos();
		pontos += pontosAntes;
		this.usuario.setPontos(pontos);
		return this.usuario;
	}

}
