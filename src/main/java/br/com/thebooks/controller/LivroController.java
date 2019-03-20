package br.com.thebooks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Estilo;
import br.com.thebooks.domain.Livro;
import br.com.thebooks.domain.Trofeu;
import br.com.thebooks.domain.Usuario;

@ManagedBean
@SessionScoped
public class LivroController {
	private String opcao;
	private String pesquisa;
	private Livro livro ;
	private Estilo estilo;
	private Facede facede;
	private List<String> listaEstilos;
	private List<Livro> livros;
	private Usuario usuario;
	private List<Usuario> usuarios;
	private boolean livrosLidos;
	private String mensagem;
	private List<Trofeu> trofeus;

	private List<String> opcoes;
	List<EntidadeDominio> resultado;
	
	public LivroController() {
		this.livro = new Livro();
		this.estilo = new Estilo();
	}
	@PostConstruct
	public void init() {
		this.usuario =(Usuario)FacesContext.getCurrentInstance().
				getExternalContext().getSessionMap().get("usuario");
		
		this.facede = new Facede(estilo);
		this.resultado = this.facede.listaEntidades(estilo, "simples");
		if(resultado!=null) {
			listaEstilos = new ArrayList<String>();
			for(EntidadeDominio entidade: this.resultado) {
				Estilo e = (Estilo)entidade;
				listaEstilos.add(e.getCategoriaLivro());
			}
		}
		Livro l = new Livro();
		this.facede = new Facede(l);
		List<EntidadeDominio> lista = this.facede.listaEntidades(l, "simples");
		if(lista!=null) {
			livros = new ArrayList<Livro>();
			for(EntidadeDominio entidade : lista) {
				Livro l2 =(Livro) entidade;
				livros.add(l2);
			}
		}
		opcoes = new ArrayList<String>();
		opcoes.add("por nome");
		opcoes.add("por estilo");
		
		Usuario user = new Usuario();
		user.setPerfil("normal");
		this.facede = new Facede(user);
		List<EntidadeDominio> listaUsuario = this.facede.listaEntidades(user, "ranking");
		if(listaUsuario!=null) {
			usuarios = new ArrayList<Usuario>();
			for(EntidadeDominio entidade : listaUsuario) {
				Usuario u2 =(Usuario) entidade;
				usuarios.add(u2);
			}
		}
		List<Trofeu> meusTrofeus = this.usuario.getTrofeus();
		if(meusTrofeus!=null) {
			trofeus = new ArrayList<Trofeu>();
			for(Trofeu trofeu :meusTrofeus) {
				trofeus.add(trofeu);
			}
		}

	}
	public void salvar() {
		for(EntidadeDominio entidade: this.resultado) {
			 Estilo e = (Estilo)entidade;
			 if(e.getCategoriaLivro().equals(estilo.getCategoriaLivro())) {
					this.livro.setEstilo(e);
			 }
		}
		this.facede = new Facede(this.livro);
		String resultado = this.facede.salvar(this.livro);
		if(resultado ==null){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	public void consulta() {
		this.mensagem =null;
		if(livrosLidos==true) {
			if(this.usuario!=null) {
				this.facede = new Facede(this.usuario);
				this.resultado = this.facede.listaEntidades(this.usuario, "id");
				this.usuario = (Usuario) resultado.get(0);
				livros = new ArrayList<Livro>();
				List<Livro> liv = this.usuario.getLivros();
				
				if(opcao.equals("por nome")){

					for(EntidadeDominio entidade : liv) {
						Livro l2 =(Livro) entidade;
						if(l2.getNomeLivro().contains(pesquisa)) 
							livros.add(l2);
					}
				} else if(opcao.equals("por estilo")){
					for(EntidadeDominio entidade : liv) {
						Livro l2 =(Livro) entidade;
						if(l2.getEstilo().getCategoriaLivro().contains(pesquisa)) 
							livros.add(l2);
					}
				}
			}
			
		}
		
		if(opcao.equals("por nome") &&
				livrosLidos==false) {
			Livro livro = new Livro();
			livro.setNomeLivro(pesquisa);
			facede = new Facede(livro);
			this.resultado = facede.listaEntidades(livro, "nome");
			if(this.resultado!=null) {
				livros = new ArrayList<Livro>();
				for(EntidadeDominio entidade : this.resultado) {
					Livro l2 =(Livro) entidade;
					livros.add(l2);
				}
			}
		} else if(opcao.equals("por estilo") &&
				livrosLidos==false) {
			Estilo e = new Estilo();
			e.setCategoriaLivro(pesquisa);
			Livro l = new Livro();
			l.setEstilo(e);
			facede = new Facede(l);
			this.resultado = facede.listaEntidades(l, "estilo");
			
			if(this.resultado!=null) {
				livros = new ArrayList<Livro>();
				for(EntidadeDominio entidade : this.resultado) {
					Livro l2 =(Livro) entidade;
					livros.add(l2);
				}
			}
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("lista-livros.xhtml");
		} catch (IOException e2) {
			e2.printStackTrace();
		}	

		
		
	}
	public void esseEuJaLi(Livro livro) {
		this.mensagem=null;
		if(this.usuario!=null &&
				livrosLidos==false) {
			List<Livro> meusLivros = this.usuario.getLivros();

			for(Livro l :meusLivros) {
				if(livro.getNomeLivro().equals(l.getNomeLivro())) {
					this.mensagem = "Atenção: Você não tem como marcar novamente o livro!";
					return;
				}
			}
			meusLivros.add(livro);

			this.usuario.setLivros(meusLivros);
			
			this.facede = new Facede(this.usuario);
			this.facede.alterar(this.usuario);
			this.mensagem = "Aviso: Livro marcado com sucesso!";

			
		} else if (livrosLidos==true) {
			this.mensagem = "Atenção: Você não tem como marcar novamente o livro!";
			
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("lista-livros.xhtml");
		} catch (IOException e2) {
			e2.printStackTrace();
		}	
	}
	
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<String> getListaEstilos() {
		return listaEstilos;
	}
	public void setListaEstilos(List<String> listaEstilos) {
		this.listaEstilos = listaEstilos;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	public List<String> getOpcoes() {
		return opcoes;
	}
	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}
	public String getOpcao() {
		return opcao;
	}
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
	public String getPesquisa() {
		return pesquisa;
	}
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	public boolean isLivrosLidos() {
		return livrosLidos;
	}
	public void setLivrosLidos(boolean livrosLidos) {
		this.livrosLidos = livrosLidos;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Trofeu> getTrofeus() {
		return trofeus;
	}
	public void setTrofeus(List<Trofeu> trofeus) {
		this.trofeus = trofeus;
	}
	
	
	
	

}
