package br.com.thebooks.negocio;

import br.com.thebooks.domain.EntidadeDominio;

public interface IStrategy {
	public EntidadeDominio processar(EntidadeDominio entidade);
}
