package com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque.entidades.Vendas;
import com.estoque.repository.vendas.VendasRepositoryQuery;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long>, VendasRepositoryQuery{

}
