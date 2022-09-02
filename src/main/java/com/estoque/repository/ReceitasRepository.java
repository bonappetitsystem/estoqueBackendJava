package com.estoque.repository;

import com.estoque.entidades.Receitas;
import com.estoque.repository.receita.ReceitasRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long>, ReceitasRepositoryQuery {



}


