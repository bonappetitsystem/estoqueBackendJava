package com.estoque.repository;

import com.estoque.entidades.EstoqueAcabado;
import com.estoque.repository.estoqueAcabado.EstoqueAcabadoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueAcabadoRepository extends JpaRepository<EstoqueAcabado, Long>, EstoqueAcabadoRepositoryQuery {


}
