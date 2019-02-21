package br.com.thebooks.persistence;

import java.util.List;

import br.com.thebooks.domain.EntidadeDominio;

public interface IDAO {
	public String salvar(EntidadeDominio entidade);
	public String alterar(EntidadeDominio entidade);
	public List<EntidadeDominio> consulta(EntidadeDominio entidade);
	public String getTipoConsulta();
	public void setTipoConsulta(String tipoConsulta);
}
