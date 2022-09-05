package com.estoque.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Deprecated
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String quantidade;

    private String valor;

    private String data;

    private String hora;

    private String formaDePagamento;

    private String troco;

    private String total;

    private String desconto;

    private String observacao;

    private String cliente;

    private String vendedor;

    private String status;

    private String tipo;

    private String idVenda;

    private String idCliente;

    private String idVendedor;

    private String idProduto;

    private String idEstoque;

    private String idEstoqueAcabados;

    private String idReceitas;

    private String idProdutosAcabados;

    private String idProdutos;

    private String idFornecedores;
}
