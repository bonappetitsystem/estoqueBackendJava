package com.estoque.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueAcabados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_produtos_acabados")
    private ProdutosAcabados produtosAcabados;
    private String quantidade;
    private String preco;
    private String descricao;


}
