package br.ufla.dcc.siscob.model.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.ufla.dcc.util.DateDeserializer;
import br.ufla.dcc.util.DateSerializer;
import br.ufla.dcc.util.SCHEMAS;

@Entity
@Table( name = "itememprestimo", schema = SCHEMAS.SISCOB )
public class ItemEmprestimo {
	
	@Id
	@GeneratedValue
	private Long id;
  private Date dataDevolucao;
  private Boolean ativo;
  
	@ManyToOne
	@JoinColumn(name="ID_PUBLICACAO", referencedColumnName = "ID", nullable=false)
	private Publicacao publicacao;
	
	@ManyToOne
  @JoinColumn(name = "ID_EMPRESTIMO", referencedColumnName = "ID", nullable=false)
	@JsonIgnore
	private Emprestimo emprestimo;
	
	public ItemEmprestimo(){
  }
	
	public ItemEmprestimo(Publicacao publicacao){
	  this.publicacao=publicacao;
	  this.dataDevolucao=null;
	}
	
	public Long getId() {
    return id;
  }
	
	@JsonIgnore
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public ItemEmprestimo setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
		return this;
	}
	
	@JsonSerialize( using = DateSerializer.class )
	public Date getDataDevolucao(){
	  return this.dataDevolucao;
	}
	
	@JsonDeserialize( using = DateDeserializer.class )
	public ItemEmprestimo setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
		return this;
	}
	
	public Publicacao getPublicacao(){
		return this.publicacao;
	}
	
	public ItemEmprestimo setPublicacao(Publicacao publicacao) {
    this.publicacao = publicacao;
    return this;
  }
	
	public ItemEmprestimo atualizaDevolucao(Date novaData){
		this.dataDevolucao = novaData;
		return this;
	}

  public Boolean getAtivo() {
    return ativo;
  }

  public ItemEmprestimo setAtivo(Boolean ativo) {
    this.ativo = ativo;
    return this;
  }

}
