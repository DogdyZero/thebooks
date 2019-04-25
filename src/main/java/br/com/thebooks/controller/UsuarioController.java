package br.com.thebooks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Estilo;
import br.com.thebooks.domain.Trofeu;
import br.com.thebooks.domain.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController {
	private Usuario usuario ;
	private Facede facede;
	private String mensagem;
	public UsuarioController() {
		this.usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void logar() {
		this.mensagem =null;
		this.facede = new Facede(this.usuario);
		List<EntidadeDominio> resultado = facede.listaEntidades(this.usuario, "login");
		if(resultado !=null){
			try {
				this.usuario = (Usuario)resultado.get(0);
				FacesContext.getCurrentInstance().getExternalContext().
					getSessionMap().put("usuario", this.usuario);
				if(this.usuario.getPerfil().equals("normal"))
					FacesContext.getCurrentInstance().getExternalContext().redirect("lista-livros.xhtml");
				else if (this.usuario.getPerfil().equals("admin"))
					FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if(!this.usuario.getLogin().equals("primeiro acesso")) {
				this.mensagem ="Atenção: Usuário ou senha invalidos!";
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} else {
				primeiroAcesso();
			}
		}
	}
	public void salvar() {
		this.usuario.setPerfil("normal");
		this.facede = new Facede(this.usuario);
		String resultado = this.facede.salvar(this.usuario);
		if(resultado !=null){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("lista-livros.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			this.mensagem ="Atenção: Dados não conferem, favor tentar novamente!";
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException e2) {
				e2.printStackTrace();
			}	
		}
		
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	private void primeiroAcesso() {
		Estilo estilo = new Estilo();
		this.facede = new Facede(estilo);
		List<EntidadeDominio> lista = facede.listaEntidades(estilo, "simples");
		if( lista==null) {
			List<Estilo> estilos = new ArrayList<Estilo>();
			estilos.add(new Estilo("ficcao cientifica"));
			estilos.add(new Estilo("romance"));
			estilos.add(new Estilo("aventura"));
			estilos.add(new Estilo("comédia"));
			estilos.add(new Estilo("ação"));
			estilos.add(new Estilo("infanto juvenil"));
			estilos.add(new Estilo("drama"));
			estilos.add(new Estilo("terror"));
			estilos.add(new Estilo("animação"));
			
			for(Estilo e :estilos) {
				facede = new Facede(e);
				facede.salvar(e);
			}
			List<Trofeu> trofeus = new ArrayList<Trofeu>();
			
			trofeus.add(new Trofeu("ficcao cientifica","ficcao.jpg"));
			trofeus.add(new Trofeu("romance","romance.jpg"));
			trofeus.add(new Trofeu("aventura","aventura.jog"));
			trofeus.add(new Trofeu("comédia","comedia.jpg"));
			trofeus.add(new Trofeu("ação","acao.jpg"));
			trofeus.add(new Trofeu("infanto juvenil","infanto.jpg"));
			trofeus.add(new Trofeu("drama","drama.jpg"));
			trofeus.add(new Trofeu("terror","terror.jpg"));
			trofeus.add(new Trofeu("animação","animacao.jpeg"));
			
			for(Trofeu e :trofeus) {
				facede = new Facede(e);
				facede.salvar(e);
			}
		}
		lista =null;
		Usuario user = new Usuario("admin","admin");
		user.setPerfil("admin");
		facede = new Facede(user);
		lista = facede.listaEntidades(user, "login");
		if(lista==null) {
			facede.salvar(user);
		}
		
	}
	
	
}
