package br.com.thebooks.adapter;

import java.util.List;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Livro;
import br.com.thebooks.domain.Trofeu;
import br.com.thebooks.domain.Usuario;
import br.com.thebooks.persistence.TrofeuDAO;

public class UsuarioTrofeuAdapter implements IAdapter {
	private Usuario usuario;
	private String categoriaAtual;
	@Override
	public int processar(EntidadeDominio entidade) {
		int contador =0;
		if(!entidade.getClass().getSimpleName().equals("Usuario"))
			return 0;
		this.usuario =(Usuario)entidade;
		List<Livro> livros = this.usuario.getLivros();
		this.categoriaAtual = livros.get(0).getEstilo().getCategoriaLivro();
		for(Livro livro :livros) {
			if(this.usuario.getLivros().get(0).getEstilo().getCategoriaLivro().
					equals(this.categoriaAtual)) {
				contador++;
			}
		}
		return contador;
	}

	@Override
	public EntidadeDominio receberEntidade(int pontos) {
		
		if(pontos>=5) {
			Trofeu trofeu = new Trofeu();
			trofeu.setNome(this.categoriaAtual);
			TrofeuDAO tDao = new TrofeuDAO(trofeu);
			tDao.setTipoConsulta("trofeu");
			List<EntidadeDominio> resultado = tDao.consulta(trofeu);
			if(resultado!=null) {
				Trofeu t = (Trofeu) resultado.get(0);
				this.usuario.getTrofeus().add(t);
			}
		}
		return this.usuario;
	}

}
