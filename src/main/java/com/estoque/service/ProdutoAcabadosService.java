package com.estoque.service;

import com.estoque.entidades.ProdutosAcabados;
import com.estoque.repository.ProdutosAcabadosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoAcabadosService {

    @Autowired
    private ProdutosAcabadosRepository produtosAcabadosRepository;

    public List<ProdutosAcabados> buscarTodos() {
        return produtosAcabadosRepository.findAll();
    }

    public ProdutosAcabados buscarPorId(Long id) {
        return produtosAcabadosRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public ProdutosAcabados cadastrar(ProdutosAcabados produtosAcabados) {
        return produtosAcabadosRepository.save(produtosAcabados);
    }

    public void deletar(Long id) {
        produtosAcabadosRepository.deleteById(id);
    }

    public ProdutosAcabados alterar(Long id, ProdutosAcabados produtosAcabados) {
        ProdutosAcabados produtosAcabadosSalvo = buscarPorId(id);
        BeanUtils.copyProperties(produtosAcabados, produtosAcabadosSalvo, "id");
        return produtosAcabadosRepository.save(produtosAcabadosSalvo);
    }


}
