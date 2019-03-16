package br.com.thebooks.adapter;

import br.com.thebooks.domain.EntidadeDominio;

public interface IAdapter {
	public int processar(EntidadeDominio entidade);
	public EntidadeDominio receberEntidade(int pontos);
}
