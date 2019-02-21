package br.com.thebooks.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController {
	private Usuario usuario ;
	private Facede facede;
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
		this.facede = new Facede(this.usuario);
		List<EntidadeDominio> resultado = facede.listaEntidades(this.usuario, "login");
		if(resultado !=null){
			try {
				this.usuario = (Usuario)resultado.get(0);
				
				if(this.usuario.getPerfil().equals("normal"))
					FacesContext.getCurrentInstance().getExternalContext().redirect("lista-livros.xhtml");
				else if (this.usuario.getPerfil().equals("admin"))
					FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");

			} catch (IOException e) {
				e.printStackTrace();
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
		}
	}
	
}
