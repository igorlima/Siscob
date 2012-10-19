package br.ufla.dcc.siscob.model.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ufla.dcc.util.SCHEMAS;
@Entity
@Table( name = "periodico", schema = SCHEMAS.SISCOB )
@JsonIgnoreProperties(ignoreUnknown=true)
public class PeriodicoAttr {

  @Id private Long id; 
  private int numEdicao;
	private String mes;
	
	public PeriodicoAttr() {
	}
	
	public PeriodicoAttr(Publicacao publicacao) {
	  if (publicacao instanceof Periodico) {
      this
      .setId(publicacao.getId())
      .setMes(((Periodico) publicacao).getMes())
      .setNumEdicao(((Periodico) publicacao).getNumEdicao());
    }
	}

  public Long getId() {
    return id;
  }

  public PeriodicoAttr setId(Long id) {
    this.id = id;
    return this;
  }

  public int getNumEdicao() {
    return numEdicao;
  }

  public PeriodicoAttr setNumEdicao(int numEdicao) {
    this.numEdicao = numEdicao;
    return this;
  }

  public String getMes() {
    return mes;
  }

  public PeriodicoAttr setMes(String mes) {
    this.mes = mes;
    return this;
  }
	
}
