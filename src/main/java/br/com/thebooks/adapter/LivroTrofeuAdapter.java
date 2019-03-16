package br.com.thebooks.adapter;


import java.util.List;

import br.com.thebooks.domain.EntidadeDominio;
import br.com.thebooks.domain.Livro;
import br.com.thebooks.domain.Usuario;

public class LivroTrofeuAdapter implements IAdapter {
	private Livro livro;
	@Override
	public int processar(EntidadeDominio entidade) {
		int contador =0;
		if(!entidade.getClass().getSimpleName().equals("Livro"))
			return 0;
		this.livro = (Livro)entidade;
		List<Usuario> usuarios = this.livro.getUsuarios();
		Usuario user = usuarios.get(0);
		List<Livro> livros = user.getLivros();
		for(Livro l:livros) {
			if(l.getEstilo().getCategoriaLivro().
					equals(this.livro.getEstilo().getCategoriaLivro())) {
				// verificar se o usuario já não ganhou o trofeu
				contador++;
			}
		}
		
		return contador;
	}

	@Override
	public EntidadeDominio receberEntidade(int pontos) {
		if(pontos>=5) {
		} 
		return  this.livro;
	}

}
