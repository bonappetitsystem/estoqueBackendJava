package com.estoque.repository.estoque;

import com.estoque.entidades.Estoque;
import com.estoque.repository.filter.EstoqueFilter;

import java.util.List;

public interface EstoqueRepositoryQuery {

    List<Estoque> filtrar(EstoqueFilter estoqueFilter);
}
