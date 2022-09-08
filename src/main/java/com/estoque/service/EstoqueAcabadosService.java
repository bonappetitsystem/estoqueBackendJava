package com.estoque.service;

import com.estoque.entidades.EstoqueAcabado;
import com.estoque.repository.EstoqueAcabadoRepository;
import com.estoque.repository.filter.EstoqueAcabadoFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueAcabadosService {

    @Autowired
    private EstoqueAcabadoRepository estoqueAcabadoRepository;

    public List<EstoqueAcabado>pesquisar(EstoqueAcabadoFilter estoqueAcabadoFilter){
        return estoqueAcabadoRepository.filtrar(estoqueAcabadoFilter);
    }

    public EstoqueAcabado buscarPorId(Long id) {
        return estoqueAcabadoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }


    public EstoqueAcabado cadastrar(EstoqueAcabado estoqueAcabado) {
        return estoqueAcabadoRepository.save(estoqueAcabado);
    }



    public void deletar(Long id) {
        estoqueAcabadoRepository.deleteById(id);
    }

    public EstoqueAcabado alterar(Long id, EstoqueAcabado estoqueAcabado) {
        EstoqueAcabado estoqueAcabadoSalvo = buscarPorId(id);
        BeanUtils.copyProperties(estoqueAcabado, estoqueAcabadoSalvo, "id");
        return estoqueAcabadoRepository.save(estoqueAcabadoSalvo);
    }

}
