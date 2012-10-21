package br.ufla.dcc.siscob.model.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
  private Date dataDaDevolucao;
  private Date dataParaDevolucao;
  private Date dataEmprestimo;
  private Boolean ativo;
  @Transient Boolean devolver;
  @Transient Boolean renovar;
  @Transient Boolean devolvido;
  
  
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
	  this.dataDaDevolucao=null;
	}
	
	public Long getId() {
    return id;
  }
	
	public ItemEmprestimo setId(Long id) {
	  this.id = id;
	  return this;
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
	public Date getDataDaDevolucao(){
	  return this.dataDaDevolucao;
	}
	
	@JsonDeserialize( using = DateDeserializer.class )
	public ItemEmprestimo setDataDaDevolucao(Date dataDaDevolucao) {
		this.dataDaDevolucao = dataDaDevolucao;
		return this;
	}
	
	@JsonSerialize( using = DateSerializer.class )
	public Date getDataParaDevolucao(){
	  return this.dataParaDevolucao;
	}
	
	@JsonDeserialize( using = DateDeserializer.class )
	public ItemEmprestimo setDataParaDevolucao(Date dataParaDevolucao) {
	  this.dataParaDevolucao = dataParaDevolucao;
	  return this;
	}
	
	@JsonSerialize( using = DateSerializer.class )
	public Date getDataEmprestimo(){
	  return this.dataEmprestimo;
	}
	
	@JsonDeserialize( using = DateDeserializer.class )
	public ItemEmprestimo setDataEmprestimo(Date dataEmprestimo) {
	  this.dataEmprestimo = dataEmprestimo;
	  return this;
	}
	
	public Publicacao getPublicacao(){
		return this.publicacao;
	}
	
	public ItemEmprestimo setPublicacao(Publicacao publicacao) {
    this.publicacao = publicacao;
    return this;
  }
	
  public Boolean getAtivo() {
    return ativo;
  }

  public ItemEmprestimo setAtivo(Boolean ativo) {
    this.ativo = ativo;
    return this;
  }

  public Boolean getDevolver() {
    return devolver;
  }

  public ItemEmprestimo setDevolver(Boolean devolver) {
    this.devolver = devolver;
    return this;
  }
  
  public Boolean getDevolvido() {
    return devolvido;
  }

  public ItemEmprestimo setDevolvido(Boolean devolvido) {
    this.devolvido = devolvido;
    return this;
  }

  public Boolean getRenovar() {
    return renovar;
  }

  public ItemEmprestimo setRenovar(Boolean renovar) {
    this.renovar = renovar;
    return this;
  }
  
}
