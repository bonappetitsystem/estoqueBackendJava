package com.estoque.service;

import com.estoque.entidades.EstoqueAcabados;
import com.estoque.repository.EstoqueAcabadosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueAcabadosService {

    @Autowired
    private EstoqueAcabadosRepository estoqueAcabadosRepository;

    public List<EstoqueAcabados> buscarTodos() {
        return estoqueAcabadosRepository.findAll();
    }

    public EstoqueAcabados buscarPorId(Long id) {
        return estoqueAcabadosRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public EstoqueAcabados cadastrar(EstoqueAcabados estoqueAcabados) {
        return estoqueAcabadosRepository.save(estoqueAcabados);
    }


    public void deletar(Long id) {
        estoqueAcabadosRepository.deleteById(id);
    }

    public EstoqueAcabados alterar(Long id, EstoqueAcabados estoqueAcabados) {
        EstoqueAcabados estoqueAcabadosSalvo = buscarPorId(id);
        BeanUtils.copyProperties(estoqueAcabados, estoqueAcabadosSalvo, "id");
        return estoqueAcabadosRepository.save(estoqueAcabadosSalvo);
    }

}
