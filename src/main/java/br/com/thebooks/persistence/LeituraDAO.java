package br.com.thebooks.persistence;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.factory.LeituraQuery;

public class LeituraDAO extends AbstractDAO {
	public LeituraDAO(EntidadeDominio entidade) {
		super.entidade = entidade;
		super.fabricaQuery = new LeituraQuery(super.entidade);
	}
}
