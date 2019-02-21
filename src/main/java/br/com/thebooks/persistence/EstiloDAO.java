package br.com.thebooks.persistence;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.factory.EstiloQuery;

public class EstiloDAO extends AbstractDAO {
	public EstiloDAO(EntidadeDominio entidade) {
		super.entidade = entidade;
		super.fabricaQuery = new EstiloQuery(super.entidade);
	}
}
