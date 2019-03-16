package br.com.thebooks.adapter;

import java.util.List;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Livro;
import br.com.thebooks.domain.Usuario;

public class LivroPontuacaoAdapter implements IAdapter {
	private Livro livro;

	@Override
	public int processar(EntidadeDominio entidade) {
		this.livro = (Livro)entidade;
		return livro.getPaginas();		
	}

	@Override
	public EntidadeDominio receberEntidade(int pontos) {
		List<Usuario> usuarios = livro.getUsuarios();
		Usuario usuario = usuarios.get(0);
		int pontosAntes = usuario.getPontos();
		pontos += pontosAntes;
		usuario.setPontos(pontos);
		usuarios.remove(0);
		usuarios.add(usuario);
		this.livro.setUsuarios(usuarios);
		return this.livro;
	}

}
