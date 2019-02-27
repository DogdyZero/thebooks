package br.com.thebooks.persistence;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.factory.TrofeuQuery;

public class TrofeuDAO extends AbstractDAO {
	public TrofeuDAO(EntidadeDominio entidade) {
		super.entidade = entidade;
		super.fabricaQuery = new TrofeuQuery(super.entidade);
	}
}
