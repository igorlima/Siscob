package br.ufla.dcc.siscob.model.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ufla.dcc.util.SCHEMAS;

@Entity
@Table( name = "livro", schema = SCHEMAS.SISCOB )
@JsonIgnoreProperties(ignoreUnknown=true)
public class LivroAttr {
  
  @Id private Long id;
	private String autores;
	private Long qtdExemplares;
	
	public LivroAttr() {
	  
	}
	
	public LivroAttr(Publicacao publicacao) {
	  if (publicacao instanceof Livro) {
	    this
	    .setId(publicacao.getId())
	    .setAutores(((Livro) publicacao).getAutores())
	    .setQtdExemplares(((Livro) publicacao).getQtdExemplares());
	  }
	}
	
  public Long getId() {
    return id;
  }

  public LivroAttr setId(Long id) {
    this.id = id;
    return this;
  }

  public String getAutores() {
    return autores;
  }

  public LivroAttr setAutores(String autores) {
    this.autores = autores;
    return this;
  }

  public Long getQtdExemplares() {
    return qtdExemplares;
  }

  public LivroAttr setQtdExemplares(Long qtdExemplares) {
    this.qtdExemplares = qtdExemplares;
    return this;
  }
	
}
