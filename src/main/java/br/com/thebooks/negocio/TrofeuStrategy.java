package br.com.thebooks.negocio;

import java.util.HashMap;
import java.util.Map;

import br.com.thebooks.adapter.IAdapter;
import br.com.thebooks.adapter.LivroTrofeuAdapter;
import br.com.thebooks.adapter.UsuarioTrofeuAdapter;
import br.com.thebooks.domain.EntidadeDominio;

public class TrofeuStrategy implements IStrategy {
	private Map<String,IAdapter> adapter;
	public TrofeuStrategy() {
		adapter = new HashMap<String, IAdapter>();
		adapter.put("Livro", new LivroTrofeuAdapter());
		adapter.put("Usuario", new UsuarioTrofeuAdapter());

	}
	@Override
	public EntidadeDominio processar(EntidadeDominio entidade) {
		IAdapter adaptador = adapter.get(entidade.getClass().getSimpleName());
		int qtdCategorias = adaptador.processar(entidade);
		if(qtdCategorias>=5) {
			return adaptador.receberEntidade(qtdCategorias);
		}
		return entidade;
	}

}
