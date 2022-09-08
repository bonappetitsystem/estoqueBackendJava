package com.estoque.repository.venda;

import com.estoque.entidades.Venda;
import com.estoque.repository.filter.VendaFilter;

import java.util.List;

public interface VendaRepositoryQuery {

    List<Venda> filtrar(VendaFilter vendaFilter);
}
