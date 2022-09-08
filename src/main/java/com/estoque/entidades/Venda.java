package com.estoque.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_estoque_acabados")
    private EstoqueAcabado estoqueAcabado;

    private String codigoVenda = "VENDA-" + Instant.now().getEpochSecond();

    @NotNull
    private int quantidade;


    private TipoPagamento formaDePagamento;

    private String troco;


    private String vendedor;
    private Instant dataVenda;
    private Instant dataAtualizacao;

    private Float valorTotalDaVenda;

    @Enumerated(EnumType.STRING)
    private StatusVenda statusVenda;

    private TipoVenda tipoVenda;

    @Embedded
    private DadosCliente dadosCliente;

    @PrePersist
    public void prePersist() {
        dataVenda = Instant.now();
        dataAtualizacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = Instant.now();
    }

}





