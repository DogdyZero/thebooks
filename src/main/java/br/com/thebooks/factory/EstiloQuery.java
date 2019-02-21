package br.com.thebooks.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Estilo;

public class EstiloQuery implements IFactoryQuery {
	private Estilo estilo;
	private String tipoConsulta;
	private Map<String,String> mapQuery;
	private Map<String,List<Object>> mapParameters;

	public EstiloQuery(EntidadeDominio entidade) {
		if(entidade.getClass().getSimpleName().equals("Estilo")) {
			this.estilo = (Estilo) entidade;
			mapQuery = new HashMap<String,String>();
			mapParameters = new HashMap<String,List<Object>>();
			
			List<Object> listSimples = new ArrayList<Object>();
			mapQuery.put("simples", "from Estilo");
			mapParameters.put("simples", listSimples);
			
			List<Object> listNome = new ArrayList<Object>();
			mapQuery.put("nome", "from Estilo where categoria like :param1");
			listNome.add("%" + this.estilo.getCategoriaLivro()+"%");

			mapParameters.put("nome", listNome);
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
