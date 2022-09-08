package com.estoque.repository;

import com.estoque.entidades.Venda;
import com.estoque.repository.venda.VendaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>, VendaRepositoryQuery {


}
