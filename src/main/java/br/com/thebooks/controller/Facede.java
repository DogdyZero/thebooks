package br.com.thebooks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Estilo;
import br.com.thebooks.domain.Leitura;
import br.com.thebooks.domain.Livro;
import br.com.thebooks.domain.Trofeu;
import br.com.thebooks.domain.Usuario;
import br.com.thebooks.persistence.EstiloDAO;
import br.com.thebooks.persistence.IDAO;
import br.com.thebooks.persistence.LeituraDAO;
import br.com.thebooks.persistence.LivroDAO;
import br.com.thebooks.persistence.TrofeuDAO;
import br.com.thebooks.persistence.UsuarioDAO;

public class Facede {
	private Map<String,IDAO> daos;
	
	public Facede(EntidadeDominio entidade) {
		daos = new HashMap<String, IDAO>();

		daos.put(Usuario.class.getName(), new UsuarioDAO(entidade));
		daos.put(Livro.class.getName(), new LivroDAO(entidade));
		daos.put(Estilo.class.getName(), new EstiloDAO(entidade));
		daos.put(Leitura.class.getName(), new LeituraDAO(entidade));
		daos.put(Trofeu.class.getName(), new TrofeuDAO(entidade));

	}
	public String salvar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		
		String msg = daos.get(nomeClasse).salvar(entidade);
		if(msg!=null) {
			return msg;
		}
		return null;
	}

	public String alterar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		String msg = daos.get(nomeClasse).salvar(entidade);
		if(msg!=null) {
			return msg;
		}
		return null;
	}

	public List<EntidadeDominio> listaEntidades(EntidadeDominio entidade, String tipoConsulta) {
		String nomeClasse = entidade.getClass().getName();
		daos.get(nomeClasse).setTipoConsulta(tipoConsulta);
		List<EntidadeDominio> resultado = daos.get(nomeClasse).consulta(entidade);
		if(resultado==null) {
			return null;
		} else {
			return resultado;
		}
	}
}
