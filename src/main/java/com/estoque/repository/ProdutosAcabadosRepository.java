package com.estoque.repository;

import com.estoque.entidades.ProdutosAcabados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosAcabadosRepository extends JpaRepository<ProdutosAcabados, Long> {

}
