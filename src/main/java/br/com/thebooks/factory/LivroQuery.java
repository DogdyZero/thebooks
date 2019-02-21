package br.com.thebooks.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Livro;

public class LivroQuery implements IFactoryQuery {
	
	private Livro livro;
	private String tipoConsulta;
	private Map<String,String> mapQuery;
	private Map<String,List<Object>> mapParameters;
	
	public LivroQuery(EntidadeDominio entidade) {
		if(entidade.getClass().getSimpleName().equals("Livro")) {
			this.livro = (Livro) entidade;
			mapQuery = new HashMap<String,String>();
			mapParameters = new HashMap<String,List<Object>>();
			
			List<Object> listSimples = new ArrayList<Object>();
			mapQuery.put("simples", "from Livro");
			mapParameters.put("simples", listSimples);
			
			List<Object> listNome = new ArrayList<Object>();
			mapQuery.put("nome", "from Livro where nome_livro like :param1");
			listNome.add("%" + this.livro.getNomeLivro()+"%");

			mapParameters.put("nome", listNome);
			
			if(this.livro.getEstilo() !=null) {
				List<Object> listTipoEstilo = new ArrayList<Object>();
				mapQuery.put("estilo", "from Livro l inner join l.estilo as estilo"
						+ " where estilo.categoria like :param1");
				listTipoEstilo.add("%" + this.livro.getEstilo().getCategoriaLivro() + "%");
				//listTipoEstilo.add(this.livro.getEstilo().getId());

				mapParameters.put("estilo", listTipoEstilo);
			}
			
		}
	}
	@Override
	public String gerarString(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
		return mapQuery.get(tipoConsulta);
	}

	@Override
	public List<Object> retornoParametros() {
		return mapParameters.get(this.tipoConsulta);
	}

}
