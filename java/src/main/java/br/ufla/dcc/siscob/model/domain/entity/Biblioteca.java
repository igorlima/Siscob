package br.ufla.dcc.siscob.model.domain.entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Biblioteca {
	private List<Usuario> usuarios;
	private List<Publicacao> publicacoes;
	private List<Emprestimo> emprestimos;
	
	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}
	
public Mensagem adicionarUsuario(Usuario usuario) {
		
		if(usuario != null) {
			usuarios.add(usuario);
			return new Mensagem(TipoMenssagem.SUCESSO, "Usuario cadastrado com sucesso.");
		}
		return new Mensagem(TipoMenssagem.ERRO, "Usuario nao foi cadastrado com sucesso.");
	}

public Mensagem adicionaPublicacao(Publicacao publicacao){
	if(existePublicacao(publicacao))
		return new Mensagem(TipoMenssagem.ERRO, "Publicac�o ja cadastrada na biblioteca.");
	
	if(publicacao != null) { 
		publicacoes.add(publicacao);
		return new Mensagem(TipoMenssagem.SUCESSO, "Livro cadastrado com sucesso.");
	}
	
	return new Mensagem(TipoMenssagem.ERRO, "Publicac�o nao foi cadastrada com sucesso");
}



public boolean existePublicacao(Publicacao publicacaoProcurada){
	
	for (Publicacao publicacao : this.publicacoes) {
		if(publicacao.getId()== publicacaoProcurada.getId())
			return true;
	}
	
	return false;
}

public Mensagem adicionarEmprestimo(Emprestimo emprestimo){
	
	if(emprestimo != null) { 
		emprestimos.add(emprestimo);
		return new Mensagem(TipoMenssagem.SUCESSO, "Emprestimo realizado com sucesso.");
	}
	
	return new Mensagem(TipoMenssagem.ERRO, "Emprestimo nao foi realizado.");
}

public void excluirPublicacao(Publicacao publicacao) throws Exception{
	if ( publicacoes.contains(publicacao) ){
		excluirPublicacaoExistente(publicacao);
	}else{
		throw new Exception("Publicac�o n�o existe");
	}
}

public Mensagem excluirPublicacaoExistente(Publicacao publicacao) throws Exception{
	if (!possuiEmprestimo( (Livro)publicacao) ){
		publicacoes.remove(publicacao);
		return new Mensagem(TipoMenssagem.SUCESSO, "Publica��o excluida com sucesso.");
	}
	
	return new Mensagem(TipoMenssagem.ERRO, "Livro esta emprestado.");
}

private boolean possuiEmprestimo (Livro livro){
	for (Emprestimo  emprestimo : emprestimos){
		if( emprestimo.possuiLivro(livro) )
			return true;
	}
	return false;
}

public Mensagem excluirUsuario(Usuario usuario) throws Exception{
	if ( usuarios.contains(usuario) ){
		excluirUsuarioExistente(usuario);
		return new Mensagem(TipoMenssagem.SUCESSO, "Usuario removido com sucesso.");
	}
	
	return new Mensagem(TipoMenssagem.ERRO, "Usuario nao foi removido.");
}

private void excluirUsuarioExistente(Usuario usuario) throws Exception{
	if (!possuiEmprestimo(usuario)){
		usuarios.remove(usuario);
	}else{
		throw new Exception("Usu�rio possui emprestimo pendente. Usu�rio n�o deletado.");
	}
}

private boolean possuiEmprestimo(Usuario usuario) {
	for (Emprestimo emprestimo : emprestimos) {
		if (usuario.equals(emprestimo.getUsuario()))
			return true;
	}
	return false;
}

public boolean pesquisarEmprestimo(int numeroEmprestimoPesquisado) {
	
	for (Emprestimo emprestimoCadastrado  : emprestimos) {			
		
		if(emprestimoCadastrado.getNumero() == numeroEmprestimoPesquisado) {
			return true;
		}
	}
	return false;
}

public Mensagem excluiEmprestimo(Emprestimo emprestimo) throws Exception{
	if (emprestimos.contains(emprestimo)){
		emprestimos.remove(emprestimo);
		return new Mensagem(TipoMenssagem.SUCESSO, "Emprestimo excluido com sucesso.");
		
	}
	
	return new Mensagem(TipoMenssagem.ERRO, "Emprestimo n�o encontrado.");
}

public void devolveItemEmprestimo(Emprestimo emprestimo, ItemEmprestimo itemEmprestimo) throws Exception{
	if( emprestimos.contains(emprestimo)){
		long hoje = GregorianCalendar.getInstance().getTimeInMillis();
		long diferencaEmDias =( ( ( ( ( hoje - emprestimo.getDataPrevDevolucao().getTimeInMillis() )/1000 ) /60 ) /60 ) /24 );
		emprestimo.devolverItemEmprestimo(itemEmprestimo);
		
		if (diferencaEmDias >= 1){
			Usuario usuario = emprestimo.getUsuario();
			GregorianCalendar dataPenalizacao = new GregorianCalendar();
			dataPenalizacao.add(GregorianCalendar.DAY_OF_MONTH, (int) diferencaEmDias);
			usuario.setDataPenalizacao(dataPenalizacao);
		}
			
	}
	else
		throw new Exception("Emprestimo n�o existe");
}

public void excluirItensEmprestimoDeUmEmprestimo(Emprestimo emprestimo) throws Exception{
	
	if(emprestimo != null & emprestimo.getItens().size() > 0) {
		for (ItemEmprestimo itemEmprestimo: emprestimo.getItens()) {
			devolveItemEmprestimo(emprestimo, itemEmprestimo);
		}
	}
}

public void devolverLivro(Emprestimo emprestimo, Publicacao publicacao) throws Exception{
	if(emprestimos.contains(emprestimo) && existePublicacao(publicacao)){
		ItemEmprestimo itemEmprestado = buscarItemEmprestimoPorCodigoDoLivro(emprestimo, publicacao.getId());
		devolveItemEmprestimo(emprestimo, itemEmprestado);
		emprestimo.devolverLivro(publicacao);
	}else{
		throw new Exception("Emprestimo ou livro n�o existente.");
	}	
}
public ItemEmprestimo buscarItemEmprestimoPorCodigoDoLivro(Emprestimo emprestimo, Long long1) {
	
	for (ItemEmprestimo itemEmprestimo : emprestimo.getItens()) {
		if(itemEmprestimo.getPublicacao().getId() == long1)
			return itemEmprestimo;
	}
	return null;
}

public void devolveTodosLivros(Emprestimo emprestimo) throws Exception{
	if (emprestimos.contains(emprestimo) ){
		emprestimo.devolverTodosLivro();
	}else{
		throw new Exception("Emprestimo n�o existente");
	}
}

public List<Publicacao> pesquisarPublicacaoPorTitulo(String titulo){
	
  List<Publicacao> pesquisa = new ArrayList<Publicacao>();
	
	for (Publicacao publicacao : this.publicacoes){
		if( publicacao.getTitulo().trim().toLowerCase().contains(titulo.trim().toLowerCase()) ){
			pesquisa.add(publicacao);
		}
	}
	
	return pesquisa.isEmpty() ? null : pesquisa;
}

public List<Livro> pesquisarLivrosPorAutor(String autor){
  List<Livro> pesquisa = new ArrayList<Livro>();
  List<Livro> livros = new ArrayList<Livro>();
	
	for (Publicacao publicacao : publicacoes){
		if( publicacao instanceof Livro ){
			livros.add((Livro) publicacao);
		}
	}
			
	for (Livro livro : livros){
		if( livro.getAutores().trim().toLowerCase().contains(autor.trim().toLowerCase()) ){
			pesquisa.add(livro);
		}
	}
	
	return pesquisa.isEmpty() ? null : pesquisa;
}

public Usuario buscarUsuarioPorCPF(String cpf) {
	
	for (Usuario usuario : this.usuarios) {
		if(usuario.getCpf().equalsIgnoreCase(cpf))
			return usuario;
	}
	
	return null;
}

public Emprestimo buscarEmprestimoPorNumero(int numero) {
	
	if(this.pesquisarEmprestimo(numero)) {
		
		for (Emprestimo emprestimo : this.emprestimos) {
			if(emprestimo.getNumero() == numero) {
				return emprestimo;
			}
		}
	}
	
	return null;
}


}


