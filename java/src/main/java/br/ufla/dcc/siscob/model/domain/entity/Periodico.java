package br.ufla.dcc.siscob.model.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue(value = "PERIODICO")
public class Periodico extends Publicacao {
	
	private int numEdicao;
	private String mes;
	
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
