package br.com.thebooks.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Livro;
import br.com.thebooks.domain.Usuario;

public class UsuarioQuery implements IFactoryQuery{
	private Usuario usuario;
	private String tipoConsulta;
	private Map<String,String> mapQuery;
	private Map<String,List<Object>> mapParameters;

	public UsuarioQuery(EntidadeDominio entidade) {
		if(entidade.getClass().getSimpleName().equals("Usuario")) {
			this.usuario = (Usuario) entidade;
			mapQuery = new HashMap<String,String>();
			mapParameters = new HashMap<String,List<Object>>();
			
			List<Object> listIdUsuario = new ArrayList<Object>();
			mapQuery.put("id", "from Usuario where id = :param1");
			listIdUsuario.add(this.usuario.getId());
			mapParameters.put("id", listIdUsuario);
			
			List<Object> listLogin = new ArrayList<Object>();
			mapQuery.put("login", "from Usuario where login = :param1 "
					+ " and senha = :param2");
			listLogin.add(this.usuario.getLogin());
			listLogin.add(this.usuario.getSenha());
			mapParameters.put("login", listLogin);
			
			List<Object> listRanking = new ArrayList<Object>();
			mapQuery.put("ranking", "from Usuario user where perfil = :param1 "
					+ " order by user.pontos desc");
			listRanking.add(this.usuario.getPerfil());
			mapParameters.put("ranking", listRanking);
			
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
