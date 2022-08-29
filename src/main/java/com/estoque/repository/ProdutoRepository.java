package com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque.entidades.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
