package br.com.thebooks.persistence;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.factory.LivroQuery;

public class LivroDAO extends AbstractDAO {
	public LivroDAO(EntidadeDominio entidade) {
		super.entidade = entidade;
		super.fabricaQuery = new LivroQuery(super.entidade);
	}

}
