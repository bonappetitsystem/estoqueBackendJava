package com.estoque.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String titulo;
    @NotNull
    @NotBlank
    @Column(length = 2000)
    private String ingredientes;
    @NotNull
    @NotBlank
    @Column(length = 2000)
    private String modoDePreparo;

    private String tempoDePreparo;

    private String rendimento;

    private String imagem;


}
