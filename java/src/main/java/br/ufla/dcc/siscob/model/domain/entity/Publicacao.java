package br.ufla.dcc.siscob.model.domain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ufla.dcc.util.SCHEMAS;
@Entity
@Table( name = "publicacao", schema = SCHEMAS.SISCOB )
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value=Publicacao.Publicacao)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Publicacao {
  
  @Transient public static final String Livro = "LIVRO";
  @Transient public static final String Periodico = "PERIODICO";
  @Transient public static final String Publicacao = "PUBLICACAO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String editora;
	private String ano;
	private Boolean ativo;
	@Column(nullable=false) 
	private String tipo;
	
	public Long getId() {
    return id;
  }
  
  public Publicacao setId(Long id) {
    this.id = id;
    return this;
  }
  
  public String getTipo() {
    return tipo;
  }
  
  public Publicacao setTipo(String tipo) {
    this.tipo = tipo;
    return this;
  }
  
	public String getTitulo() {
		return titulo;
	}
	
	public Publicacao setTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}
	
	public String getEditora() {
		return editora;
	}
	
	public Publicacao setEditora(String editora) {
		this.editora = editora;
		return this;
	}
	
	public String getAno() {
		return ano;
	}
	
	public Publicacao setAno(String ano) {
		this.ano = ano;
		return this;
	}

  public Boolean getAtivo() {
    return ativo;
  }

  public Publicacao setAtivo(Boolean ativo) {
    this.ativo = ativo;
    return this;
  }

}
