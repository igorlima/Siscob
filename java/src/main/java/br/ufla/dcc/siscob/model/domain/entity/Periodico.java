package br.ufla.dcc.siscob.model.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Periodico extends Publicacao {
	
  @Id
  @GeneratedValue
  @Column(name = "Usuario_id")
  private Long id;
  private int numEdicao;
	private String mes;
	
	public Long getId() {
    return id;
  }
	
	public int getNumEdicao() {
		return numEdicao;
	}
	
	public void setNumEdicao(int numEdicao) {
		this.numEdicao = numEdicao;
	}
	
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}
}
