package com.estoque.repository;

import com.estoque.entidades.EstoqueAcabados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueAcabadosRepository extends JpaRepository<EstoqueAcabados, Long> {


}
