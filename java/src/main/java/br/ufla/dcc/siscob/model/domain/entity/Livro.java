package br.ufla.dcc.siscob.model.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufla.dcc.util.SCHEMAS;

@Entity
@Table( name = "livro", schema = SCHEMAS.SISCOB )
@DiscriminatorValue(value = Publicacao.Livro)
public class Livro extends Publicacao{
  
	private String autores;
	private int qtdExemplares;
	
	@ManyToOne
	@JoinColumn( name = "ID_ITEMEMPRESTIMO", referencedColumnName = "ID" )
	private ItemEmprestimo itemEmprestimo;
	
  public ItemEmprestimo getItemEmprestimo() {
    return itemEmprestimo;
  }
	
	public String getAutores() {
		return autores;
	}
	
  public void setAutores(String autores) {
		this.autores = autores;
	}
	
	public Integer getQtdExemplares() {
		return qtdExemplares;
	}
	
	public void setQtdExemplares(Integer qtdExemplares) {
		this.qtdExemplares = qtdExemplares;
	}
	
	public Livro incrementarQuantidade() {
		return this.incrementarQuantidade(1);

	}

	private Livro incrementarQuantidade(int incremento) {
		this.qtdExemplares += incremento;
		return this;
	}

	private Livro decrementarQuantidade(int decremento) throws Exception {
		if (this.qtdExemplares - decremento < 0)
			throw new Exception("Exemplares já esgotados");

		this.qtdExemplares -= decremento;
		return this;
	}

	public Livro decrementarQuantidade() throws Exception {
		return this.decrementarQuantidade(1);
	}

}
