package com.hortin.HORTIN.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String User;
	@Column(nullable = false)
	private String Nome;
	@Column(nullable = false)
	private String Senha;
	private String Email;
	private String Celular;
	private Character tipoAcesso;
	private String CPF;

	/*endereco*/
	private String EnderecoRua;
	private String EnderecoNumero;
	private String EnderecoComplemento;

	private String EnderecoBairro;
	private String EnderecoCidade;
	private String EnderecoEstado;
	private String EnderecoPais;
	private String CEP;

	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Character getTipoAcesso() {
		return tipoAcesso;
	}
	public void setTipoAcesso(Character tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	
	public Usuario() {
		super();
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getEnderecoNumero() {
		return EnderecoNumero;
	}
	public void setEnderecoNumero(String enderecoNumero) {
		EnderecoNumero = enderecoNumero;
	}
	public String getEnderecoComplemento() {
		return EnderecoComplemento;
	}
	public void setEnderecoComplemento(String enderecoComplemento) {
		EnderecoComplemento = enderecoComplemento;
	}

	public String getUser() {
		return User;
	}
	public void setUser(String userName) {
		User = userName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCelular() {
		return Celular;
	}
	public void setCelular(String celular) {
		Celular = celular;
	}
	public String getEnderecoRua() {
		return EnderecoRua;
	}
	public void setEnderecoRua(String endereçoRua) {
		EnderecoRua = endereçoRua;
	}
	public String getEnderecoBairro() {
		return EnderecoBairro;
	}
	public void setEnderecoBairro(String enderecoBairro) {
		EnderecoBairro = enderecoBairro;
	}
	public String getEnderecoCidade() {
		return EnderecoCidade;
	}
	public void setEnderecoCidade(String enderecoCidade) {
		EnderecoCidade = enderecoCidade;
	}
	public String getEnderecoEstado() {
		return EnderecoEstado;
	}
	public void setEnderecoEstado(String enderecoEstado) {
		EnderecoEstado = enderecoEstado;
	}
	public String getEnderecoPais() {
		return EnderecoPais;
	}
	public void setEnderecoPais(String enderecoPais) {
		EnderecoPais = enderecoPais;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
}
