package com.estoque.repository.vendas;

import java.util.List;

import com.estoque.entidades.Vendas;
import com.estoque.repository.filter.VendasFilter;

public interface VendasRepositoryQuery {

    List<Vendas> filtrar(VendasFilter vendaFilter);

}
