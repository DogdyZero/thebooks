package br.com.thebooks.factory;

import java.util.List;


public interface IFactoryQuery {
	public String gerarString(String tipoConsulta);
	public List<Object> retornoParametros();
}
