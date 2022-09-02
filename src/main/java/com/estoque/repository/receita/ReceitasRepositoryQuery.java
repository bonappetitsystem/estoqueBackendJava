package com.estoque.repository.receita;

import com.estoque.entidades.Receitas;
import com.estoque.repository.filter.ReceitaFilter;

import java.util.List;

public interface ReceitasRepositoryQuery {

    List<Receitas> filtrar(ReceitaFilter receitaFilter);
}
