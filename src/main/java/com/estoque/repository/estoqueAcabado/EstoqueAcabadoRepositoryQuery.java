package com.estoque.repository.estoqueAcabado;

import com.estoque.entidades.EstoqueAcabado;
import com.estoque.repository.filter.EstoqueAcabadoFilter;

import java.util.List;

public interface EstoqueAcabadoRepositoryQuery {

    List<EstoqueAcabado> filtrar(EstoqueAcabadoFilter estoqueAcabadoFilter);

}
