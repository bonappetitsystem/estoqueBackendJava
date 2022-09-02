package com.estoque.service;

import com.estoque.entidades.Estoque;
import com.estoque.entidades.Receitas;
import com.estoque.repository.ReceitasRepository;
import com.estoque.repository.filter.ReceitaFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

    public List<Receitas> pesquisar(ReceitaFilter receitaFilter) {
        return receitasRepository.filtrar(receitaFilter);
    }

    public Receitas buscarPorId(Long id){
        return receitasRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Receitas cadastrar(Receitas receitas){
        return receitasRepository.save(receitas);
    }

    public Receitas atualizar(Long id, Receitas receitas){
        Receitas receitasSalva = buscarPorId(id);
        BeanUtils.copyProperties(receitas, receitasSalva, "id");
        return receitasRepository.save(receitasSalva);
    }

    public void deletar(Long id){
        this.buscarPorId(id);
        receitasRepository.deleteById(id);
    }


}
