package br.com.thebooks.persistence;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.factory.UsuarioQuery;

public class UsuarioDAO extends AbstractDAO {
	public UsuarioDAO(EntidadeDominio entidade) {
		super.entidade = entidade;
		super.fabricaQuery = new UsuarioQuery(super.entidade);
	}
}
