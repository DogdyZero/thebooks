package br.com.thebooks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Estilo;
import br.com.thebooks.domain.Leitura;
import br.com.thebooks.domain.Livro;
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
		if(opcao.equals("por nome")) {
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
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("lista-livros.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		} else if(opcao.equals("por estilo")) {
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
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("lista-livros.xhtml");
				} catch (IOException e2) {
					e2.printStackTrace();
				}	
			}

		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,Object> map =facesContext.getExternalContext().getSessionMap();
		
		
	}
	public void esseEuJaLi(Livro livro) {
		if(this.usuario!=null) {
			Leitura leitura = new Leitura();
			leitura.setLeitura(true);
			leitura.setUsuario(this.usuario);
			leitura.setLivro(livro);
			facede = new Facede(leitura);
			String resultado = facede.salvar(leitura);
			
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
	
	

}
