package br.com.thebooks.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Estilo;
import br.com.thebooks.domain.Livro;
import br.com.thebooks.domain.Trofeu;
import br.com.thebooks.domain.Usuario;
import br.com.thebooks.negocio.IStrategy;
import br.com.thebooks.negocio.PontuacaoStrategy;
import br.com.thebooks.negocio.TrofeuStrategy;
import br.com.thebooks.persistence.EstiloDAO;
import br.com.thebooks.persistence.IDAO;
import br.com.thebooks.persistence.LivroDAO;
import br.com.thebooks.persistence.TrofeuDAO;
import br.com.thebooks.persistence.UsuarioDAO;

public class Facede {
	private Map<String,IDAO> daos;
	private Map<String,List<IStrategy>> rns;
	
	public Facede(EntidadeDominio entidade) {
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, List<IStrategy>>();

		daos.put(Usuario.class.getName(), new UsuarioDAO(entidade));
		daos.put(Livro.class.getName(), new LivroDAO(entidade));
		daos.put(Estilo.class.getName(), new EstiloDAO(entidade));
		daos.put(Trofeu.class.getName(), new TrofeuDAO(entidade));
		
		
		List<IStrategy> listAlterarStrategy = new ArrayList<IStrategy>();
		PontuacaoStrategy pontuacaoStrategy = new PontuacaoStrategy();
		TrofeuStrategy trofeuStrategy = new TrofeuStrategy();
		listAlterarStrategy.add(pontuacaoStrategy);
		listAlterarStrategy.add(trofeuStrategy);
		rns.put("ALTERAR",listAlterarStrategy);

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
		String msg = null;
		List<IStrategy> regras = rns.get("ALTERAR");
		
		for (IStrategy regra : regras) {
			entidade = regra.processar(entidade);
		}
		
		msg = daos.get(nomeClasse).alterar(entidade);
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
