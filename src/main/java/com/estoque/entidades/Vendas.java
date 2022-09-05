package com.estoque.entidades;


import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_estoque_acabado")
    private EstoqueAcabados estoqueAcabado;

    private String codigoVenda = "VENDA-" + Instant.now().getEpochSecond();

    private int quantidadeUnitaria;

    private String valor;

    private Instant dataVenda;

    private String formaDePagamento;

    private String troco;

    private String total;

    private String desconto;

    private String observacao;

    private String cliente;

    private String vendedor;

    @Enumerated(EnumType.STRING)
    private StatusVenda status;
    
    private Instant dataAtualizacao;
    
    @PrePersist
    public void prepersist() {
    	dataVenda = Instant.now();
    }
    
    @PreUpdate
    public void preupdate() {
    	dataAtualizacao = Instant.now();
    }
}
