package com.estoque.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estoque")
public class Estoque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_estoque;
	
	@Column(nullable = false)
	private Float quantidade;
	
	@Column(nullable = false)
	private Long quantidade_unitaria;
	
	@Column(nullable = false)
	private Date validade;
	
	@NotBlank(message = "Nome é obrigatório")
	@Column(length = 30, nullable = false)
	private String lote;
	
	@Size(min = 1, max = 2)
	@Column(length = 3, nullable = false)
	private String unidade_medida;
	
	@Column(nullable = false)
	private Float preco;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto id_produto; 
	
	public Estoque() {
		// TODO Auto-generated constructor stub
	}
	
	public Estoque(Float quantidade, Date validade, String lote, String unidade_medida, Float preco) {
		this.quantidade = quantidade;
		this.validade = validade;
		this.lote = lote;
		this.unidade_medida = unidade_medida;
		this.preco = preco;
	}

	
	public Long getQuantidade_unitaria() {
		return quantidade_unitaria;
	}
	public void setQuantidade_unitaria(Long quantidade_unitaria) {
		this.quantidade_unitaria = quantidade_unitaria;
	}
	public Long getId_estoque() {
		return id_estoque;
	}
	public void setId_estoque(Long id_estoque) {
		this.id_estoque = id_estoque;
	}
	public Float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getUnidade_medida() {
		return unidade_medida;
	}
	public void setUnidade_medida(String unidade_medida) {
		this.unidade_medida = unidade_medida;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Produto getId_produto() {
		return id_produto;
	}

	public void setId_produto(Produto id_produto) {
		this.id_produto = id_produto;
	}
	
}
