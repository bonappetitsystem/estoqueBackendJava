package com.estoque.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estoque_acabados")
public class EstoqueAcabado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receitas receitas;

    private int quantidade;
    private Float preco;
    private String descricao;
    private String nomeReceita;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeValidade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeCadastro;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeAtualizacao;

    @JsonIgnore
    private boolean status;

    public String getNomeReceita() {
        assert receitas != null;
        return receitas.getTitulo();
    }

    @PrePersist
    public void prePersist() {
        dataDeCadastro = LocalDate.now();
        dataDeAtualizacao = LocalDate.now();
        status = true;
    }

}
