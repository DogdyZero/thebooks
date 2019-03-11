package br.com.thebooks.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Trofeu;

public class TrofeuQuery implements IFactoryQuery {
	private Trofeu trofeu;
	private String tipoConsulta;
	private Map<String,String> mapQuery;
	private Map<String,List<Object>> mapParameters;

	public TrofeuQuery(EntidadeDominio entidade) {
		if(entidade.getClass().getSimpleName().equals("Trofeu")) {
			this.trofeu = (Trofeu) entidade;
			mapQuery = new HashMap<String,String>();
			mapParameters = new HashMap<String,List<Object>>();
			
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
