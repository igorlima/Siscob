package br.ufla.dcc.siscob.model.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ufla.dcc.util.SCHEMAS;

@Entity
@Table( name = "livro", schema = SCHEMAS.SISCOB )
@DiscriminatorValue(value = Publicacao.Livro)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Livro extends Publicacao{

	private String autores;
	private Long qtdExemplares;
	
	public Livro() {
	  
	}
	
	public Livro(Publicacao publicacao) {
	  this
	  .setId(publicacao.getId())
	  .setTitulo(publicacao.getTitulo())
	  .setEditora(publicacao.getEditora())
	  .setAno(publicacao.getAno());
	}
	
	public String getAutores() {
		return autores;
	}
	
  public Livro setAutores(String autores) {
		this.autores = autores;
		return this;
	}
	
	public Long getQtdExemplares() {
		return qtdExemplares;
	}
	
	public Livro setQtdExemplares(Long qtdExemplares) {
		this.qtdExemplares = qtdExemplares;
		return this;
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
