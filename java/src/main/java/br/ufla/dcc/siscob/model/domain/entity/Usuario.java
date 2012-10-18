package br.ufla.dcc.siscob.model.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.ufla.dcc.util.DateDeserializer;
import br.ufla.dcc.util.DateSerializer;
import br.ufla.dcc.util.SCHEMAS;

@Entity
@Table( name = "usuario", schema = SCHEMAS.SISCOB )
public class Usuario {
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String nome;
  private String endereco;
  private String cpf;
  private String telefone;
  private Date dataPenalizacao;
  
  @OneToMany(mappedBy="usuario" , cascade = CascadeType.ALL)
  private List<Emprestimo> emprestimos;
  
  public Long getId() {
    return id;
  }
  
  public Usuario setId(Long id) {
    this.id = id;
    return this;
  }
  
  public List<Emprestimo> getEmprestimos() {
    return emprestimos;
  }
  
  public Usuario setEmprestimos(List<Emprestimo> emprestimos) {
    this.emprestimos = emprestimos;
    return this;
  }
  
  public String getNome() {
    return nome;
  }
  
  public Usuario setNome(String nome) {
    this.nome = nome;
    return this;
  }
  
  public String getEndereco() {
    return endereco;
  }
  
  public Usuario setEndereco(String endereco) {
    this.endereco = endereco;
    return this;
  }
  
  public String getCpf() {
    return cpf;
  }
  
  public Usuario setCpf(String cpf) {
    this.cpf = cpf;
    return this;
  }
  
  public String getTelefone() {
    return telefone;
  }
  
  public Usuario setTelefone(String telefone) {
    this.telefone = telefone;
    return this;
  }
  
  @JsonSerialize( using = DateSerializer.class )
  public Date getDataPenalizacao() {
    return dataPenalizacao;
  }
  
  @JsonDeserialize( using = DateDeserializer.class )
  public Usuario setDataPenalizacao(Date dataPenalizacao) {
    this.dataPenalizacao = dataPenalizacao;
    return this;
  }

}
