package com.estoque.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotBlank
	private String descricao;

}
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id_produto;
//	@Column(length = 30, nullable = false)
//	private String descricao;
//
//
//	public Produto() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public Produto(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public Long getId_produto() {
//		return id_produto;
//	}
//	public void setId_produto(Long id_produto) {
//		this.id_produto = id_produto;
//	}
//	public String getDescricao() {
//		return descricao;
//	}
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//
//}
