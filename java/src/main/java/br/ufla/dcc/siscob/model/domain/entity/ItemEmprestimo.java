package br.ufla.dcc.siscob.model.domain.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufla.dcc.util.SCHEMAS;

@Entity
@Table( name = "itememprestimo", schema = SCHEMAS.SISCOB )
public class ItemEmprestimo {
	
	@Id
	@GeneratedValue
	private Long id;
	
  private Date dataDevolucao;
  private Boolean ativo;
  
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Publicacao.class )
	@JoinColumn(name="ID_PUBLICACAO", nullable=false)
	private Publicacao publicacao;
	
	@ManyToOne(targetEntity = Emprestimo.class)
	@JoinColumn(name="ID_EMPRESTIMO", nullable=false)
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
	
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public Date getDataDevolucao(){
		return this.dataDevolucao;
	}
	
	public Publicacao getPublicacao(){
		return this.publicacao;
	}
	
	public void atualizaDevolucao(Date novaData){
		this.dataDevolucao = novaData;
	}

  public Boolean getAtivo() {
    return ativo;
  }

  public ItemEmprestimo setAtivo(Boolean ativo) {
    this.ativo = ativo;
    return this;
  }

}
