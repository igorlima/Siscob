package br.ufla.dcc.siscob.model.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataPenalizacao() {
		return dataPenalizacao;
	}
	public void setDataPenalizacao(Date dataPenalizacao) {
		this.dataPenalizacao = dataPenalizacao;
	}

}
