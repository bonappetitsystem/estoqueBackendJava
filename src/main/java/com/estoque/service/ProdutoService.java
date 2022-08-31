package com.estoque.service;

import java.util.List;

import com.estoque.repository.filter.ProdutoFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estoque.entidades.Produto;
import com.estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> pesquisar(ProdutoFilter produtoFilter) {
		return produtoRepository.filtrar(produtoFilter);
	}
	
	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	public Produto cadastrar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deletar(Long id) {
//		buscarPorId(id);
		produtoRepository.deleteById(id);
	}
	
	public Produto alterar(Long id, Produto produto) {
		Produto produtoSalvo = buscarPorId(id);
		BeanUtils.copyProperties(produto, produtoSalvo, "id_produto");
		return produtoRepository.save(produtoSalvo);
	}

}
