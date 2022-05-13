package com.hortin.HORTIN.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String NomeProduto;
	private Double ValorProduto;
	private String DescricaoProduto;
	private Integer QuantidadeProduto;
	@ManyToOne
	@NotNull
	private Usuario vendedor;

	public Produto() {
	}

	public Integer getQuantidadeProduto() {
		return QuantidadeProduto;
	}

	public void setQuantidadeProduto(Integer quantidade) {
		QuantidadeProduto = quantidade;
	}
	
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	public Long getId_produto() {
		return id;
	}
	public String getNomeProduto() {
		return NomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}
	public Double getValorProduto() {
		return ValorProduto;
	}
	public void setValorProduto(Double valorProduto) {
		ValorProduto = valorProduto;
	}
	public String getDescricaoProduto() {
		return DescricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		DescricaoProduto = descricaoProduto;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", NomeProduto=" + NomeProduto + ", ValorProduto=" + ValorProduto
				+ ", DescricaoProduto=" + DescricaoProduto + "]";
	}
	
	
}
