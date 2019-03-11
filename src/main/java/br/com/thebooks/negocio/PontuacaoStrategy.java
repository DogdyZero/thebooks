package br.com.thebooks.negocio;

import java.util.HashMap;
import java.util.Map;

import br.com.thebooks.adapter.IAdapter;
import br.com.thebooks.adapter.LivroAdapter;
import br.com.thebooks.adapter.UsuarioAdapter;
import br.com.thebooks.domain.EntidadeDominio;

public class PontuacaoStrategy implements IStrategy {
	private Map<String,IAdapter> rns;
	public PontuacaoStrategy() {
		rns = new HashMap<String, IAdapter>();
		rns.put("Usuario", new UsuarioAdapter());
		rns.put("Livro", new LivroAdapter());
	}
	@Override
	public EntidadeDominio processar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getSimpleName();
		IAdapter adapter = rns.get(nomeClasse);
		int pontos = adapter.processarPaginas(entidade);
		if(pontos<=100)
			pontos = 1;
		else
			pontos = (pontos%10)+1;
		entidade = adapter.receberEntidade(pontos);
		return entidade;
	}
	
}
