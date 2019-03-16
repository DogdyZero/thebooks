package br.com.thebooks.negocio;

import java.util.HashMap;
import java.util.Map;

import br.com.thebooks.adapter.IAdapter;
import br.com.thebooks.adapter.LivroPontuacaoAdapter;
import br.com.thebooks.adapter.UsuarioPontuacaoAdapter;
import br.com.thebooks.domain.EntidadeDominio;

public class PontuacaoStrategy implements IStrategy {
	private Map<String,IAdapter> rns;
	public PontuacaoStrategy() {
		rns = new HashMap<String, IAdapter>();
		rns.put("Usuario", new UsuarioPontuacaoAdapter());
		rns.put("Livro", new LivroPontuacaoAdapter());
	}
	@Override
	public EntidadeDominio processar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getSimpleName();
		IAdapter adapter = rns.get(nomeClasse);
		int pontos = adapter.processar(entidade);
		if(pontos<=100)
			pontos = 1;
		else
			pontos = (pontos%10)+1;
		entidade = adapter.receberEntidade(pontos);
		return entidade;
	}
	
}
