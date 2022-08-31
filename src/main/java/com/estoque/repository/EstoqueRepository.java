package com.estoque.repository;

import com.estoque.repository.estoque.EstoqueRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque.entidades.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>, EstoqueRepositoryQuery {


	
}
