package br.com.thebooks.adapter;

import br.com.thebooks.domain.EntidadeDominio;

public interface IAdapter {
	public int processarPaginas(EntidadeDominio entidade);
	public EntidadeDominio receberEntidade(int pontos);
}
