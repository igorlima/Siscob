package br.ufla.dcc.siscob.model.domain.entity;

import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemEmprestimo {
	
	@Id
	@GeneratedValue
	private Long id;
	
  private GregorianCalendar dataDevolucao;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Publicacao.class )
	@JoinColumn(name="ID_PUBLICACAO", nullable=false)
	private Publicacao publicacao;
	
	@ManyToOne(targetEntity = Emprestimo.class)
	private Emprestimo emprestimo;
	
	public Long getId() {
    return id;
  }
	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public ItemEmprestimo(Publicacao publicacao){
		this.publicacao=publicacao;
		this.dataDevolucao=null;
	}
	
	public void setDataDevolucao(GregorianCalendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public GregorianCalendar getDataDevolucao(){
		return this.dataDevolucao;
	}
	
	public Publicacao getPublicacao(){
		return this.publicacao;
	}
	
	public void atualizaDevolucao(GregorianCalendar novaData){
		this.dataDevolucao = novaData;
	}

}
