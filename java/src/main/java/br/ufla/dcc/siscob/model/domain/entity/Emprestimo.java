package br.ufla.dcc.siscob.model.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.ufla.dcc.util.SCHEMAS;
@Entity
@Table( name = "emprestimo", schema = SCHEMAS.SISCOB )
public class Emprestimo {
	
	@Id
	private int id;
	private Date dataEmprestimo;
	private Date dataDevolucao;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(  mappedBy = "emprestimo" )
	private List<ItemEmprestimo> itens = new ArrayList<ItemEmprestimo>();
	
	public void setItens(ArrayList<ItemEmprestimo> itens) {
		this.itens = itens;
	}
	
	public Emprestimo(int id, Date dataEmprestimo, Date dataDevolucao, Usuario usuario ){
		this.id = id;
		this.dataDevolucao = dataDevolucao;
		this.usuario = usuario;
		dataDevolucao = dataEmprestimo;
		
	}

	public int getId() {
		return id;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
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
			itemEmprestimo.atualizaDevolucao(new Date());
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
	
	public List<ItemEmprestimo> getItens() {
		return itens;
	}
	
}
