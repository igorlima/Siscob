package br.ufla.dcc.siscob.model.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.ufla.dcc.util.SCHEMAS;
@Entity
@Table( name = "emprestimo", schema = SCHEMAS.SISCOB )
public class Emprestimo {
	
	@Id
	@GeneratedValue
	private Long id;
	private Boolean ativo;
	
	@NotNull
	@ManyToOne
	@JoinColumn( name = "ID_USUARIO", referencedColumnName = "ID" )
	private Usuario usuario;
	
	@OneToMany( cascade=CascadeType.ALL, fetch = FetchType.EAGER )
  @JoinColumn( name = "ID_EMPRESTIMO", referencedColumnName = "ID" )
	
	private List<ItemEmprestimo> itens = new ArrayList<ItemEmprestimo>();
	
	public Emprestimo(){
	  
	}
	
	public Emprestimo(Long id, Usuario usuario ) {
		this.id = id;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}
	
	public Emprestimo setId(Long id) {
	  this.id = id;
	  return this;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Emprestimo setUsuario(Usuario usuario) {
	  this.usuario = usuario;
	  return this;
	}
	
	public void setItens(ArrayList<ItemEmprestimo> itens) {
    this.itens = itens;
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
	
	public Emprestimo setItens(List<ItemEmprestimo> itens) {
    this.itens = itens;
    return this;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public Emprestimo setAtivo(Boolean ativo) {
    this.ativo = ativo;
    return this;
  }
	
}
