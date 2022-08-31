package com.estoque.repository.produto;


import com.estoque.entidades.Produto;
import com.estoque.repository.filter.ProdutoFilter;

import java.util.List;

public interface ProdutoRepositoryQuery {

    List<Produto> filtrar(ProdutoFilter produtoFilter);

}
