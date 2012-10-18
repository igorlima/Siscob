package br.ufla.dcc.siscob.model.domain.entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
@Entity
public class Emprestimo {
	
	@Id
	private int numero;
	private GregorianCalendar dataEmprestimo;
	private GregorianCalendar dataPrevDevolucao;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(  mappedBy = "emprestimo" )
	private List<ItemEmprestimo> itens = new ArrayList<ItemEmprestimo>();
	
	public void setItens(ArrayList<ItemEmprestimo> itens) {
		this.itens = itens;
	}
	
	public Emprestimo(int numero, GregorianCalendar dataEmprestimo, GregorianCalendar dataPrevDevolucao, Usuario usuario ){
		this.numero = numero;
		this.dataPrevDevolucao = dataPrevDevolucao;
		this.usuario = usuario;
		dataPrevDevolucao = dataEmprestimo;
		
	}

	public int getNumero() {
		return numero;
	}

	public GregorianCalendar getDataEmprestimo() {
		return dataEmprestimo;
	}

	public GregorianCalendar getDataPrevDevolucao() {
		return dataPrevDevolucao;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void adicionarItem(ItemEmprestimo itemEmprestimo) throws Exception{
			((Livro) itemEmprestimo.getPublicacao()).decrementarQuantidade();
			itens.add(itemEmprestimo);
	}
	
	public void adicionarItem(Livro livro) throws Exception {
		adicionarItem(new ItemEmprestimo(livro));
	}

	public Livro devolverLivro(Publicacao publicacao) throws Exception{
		Biblioteca b = new Biblioteca();
		b.devolverLivro(this, publicacao);
		return ((Livro) publicacao).incrementarQuantidade();
	}
	
	public void devolverItemEmprestimo(ItemEmprestimo itemEmprestimo) throws Exception {
		if (itens.contains(itemEmprestimo)) {
			itemEmprestimo.atualizaDevolucao(new GregorianCalendar());
			devolverLivro(itemEmprestimo.getPublicacao());
			itens.remove( itemEmprestimo );
		} else {
			throw new Exception("Este livro não foi emprestado");
		}
	}
	
	public void devolverTodosLivro() {
		while (!itens.isEmpty()) {
			try {
				devolverItemEmprestimo( itens.get(0) );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean possuiLivro(Livro livro) {
		for (ItemEmprestimo itemEmprestimo : itens) {
			if (livro.equals(itemEmprestimo.getPublicacao()))
				return true;
		}
		return false;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public List<ItemEmprestimo> getItens() {
		return itens;
	}
}
