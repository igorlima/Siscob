package br.ufla.dcc.siscob.model.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ufla.dcc.util.SCHEMAS;
@Entity
@Table( name = "periodico", schema = SCHEMAS.SISCOB )
@DiscriminatorValue(value = Publicacao.Periodico)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Periodico extends Publicacao {
	
  private int numEdicao;
	private String mes;
	
	public Periodico() {
	  
	}
	
	public Periodico(Publicacao publicacao) {
	  this
    .setId(publicacao.getId())
    .setTitulo(publicacao.getTitulo())
    .setEditora(publicacao.getEditora())
    .setAno(publicacao.getAno());
	}
	
	public int getNumEdicao() {
		return numEdicao;
	}
	
	public Periodico setNumEdicao(int numEdicao) {
		this.numEdicao = numEdicao;
		return this;
	}
	
	public String getMes() {
		return mes;
	}
	
	public Periodico setMes(String mes) {
		this.mes = mes;
		return this;
	}
	
}
